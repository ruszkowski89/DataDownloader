package com.ruszkowski.datadownloader.externalapiconfig;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ExternalApiConfigServiceTest {

    @Autowired
    private ExternalApiConfigService cut;

    @MockBean
    private ExternalApiConfigRepository repo;

    @Test
    void testGenerateUrl() throws ConfigNotFoundException {
        ExternalApiConfig config = new ExternalApiConfig();
        config.setUrl("http://test.com/{parameter}");

        Mockito.when(repo.findByType(ExternalApiConfigType.GITHUB)).thenReturn(Optional.of(config));


        URI result = cut.generateUrl(ExternalApiConfigType.GITHUB, "value");

        assertEquals("http://test.com/value", result.toString());
    }

    @Test
    void givenNoConfigSpecifiedWhenGenerateUrlThenConfigNotFoundExceptionIsThrown() {
        Mockito.when(repo.findByType(ExternalApiConfigType.GITHUB)).thenReturn(Optional.empty());

        assertThrows(ConfigNotFoundException.class, () -> cut.generateUrl(ExternalApiConfigType.GITHUB, "value"));
    }
}
