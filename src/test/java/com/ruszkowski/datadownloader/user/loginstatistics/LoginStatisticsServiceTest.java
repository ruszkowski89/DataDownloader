package com.ruszkowski.datadownloader.user.loginstatistics;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
class LoginStatisticsServiceTest {

    private static final String LOGIN = "testLogin";
    @Autowired
    private LoginStatisticsService cut;

    @MockBean
    private LoginStatisticsRepository repo;

    @Test
    void givenRequestCountIs2WhenUpdateLoginStatisticsThenRequestCountShouldBe3() {
        LoginStatistics stats = new LoginStatistics(LOGIN, 2);

        Mockito.when(repo.findByLogin(LOGIN)).thenReturn(Optional.of(stats));

        ArgumentCaptor<LoginStatistics> ac = ArgumentCaptor.forClass(LoginStatistics.class);


        cut.updateLoginStatistics(LOGIN);

        verify(repo).save(ac.capture());
        assertEquals(3, ac.getValue().getRequestCount());
    }
}
