package com.ruszkowski.datadownloader.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruszkowski.datadownloader.externalapiconfig.ConfigNotFoundException;
import com.ruszkowski.datadownloader.externalapiconfig.ExternalApiConfigService;
import com.ruszkowski.datadownloader.httprequestexecutor.HttpRequestExecutionException;
import com.ruszkowski.datadownloader.httprequestexecutor.HttpRequestExecutor;
import com.ruszkowski.datadownloader.user.loginstatistics.LoginStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.ruszkowski.datadownloader.externalapiconfig.ExternalApiConfigType.GITHUB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    private static final String GITHUB_JSON = """
            {
              "login": "testUser",
              "id": 29784381,
              "avatar_url": "https://avatars.githubusercontent.com/u/29784381?v=4",
              "name": "Test User",
              "public_repos": 18,
              "followers": 0,
              "created_at": "2017-06-29T19:53:56Z"
            }""";
    @Autowired
    private UserService cut;
    @MockBean
    private LoginStatisticsService loginStatsService;
    @MockBean
    private ExternalApiConfigService apiService;
    @MockBean
    private HttpRequestExecutor requestExecutor;

    @Test
    void testDownload() throws ConfigNotFoundException, HttpRequestExecutionException, MalformedURLException, URISyntaxException, JsonProcessingException {
        URI uri = URI.create("http://test.com/test");

        when(apiService.generateUrl(GITHUB, "test")).thenReturn(uri);
        when(requestExecutor.downloadData(uri)).thenReturn(GITHUB_JSON);

        UserDto result = cut.download("test");

        assertEquals(0, result.getCalculations().intValue());
        assertEquals("testUser", result.getLogin());
        assertEquals("https://avatars.githubusercontent.com/u/29784381?v=4", result.getAvatarUrl().toString());
        assertEquals("Test User", result.getName());
        assertEquals(29784381, result.getId());
        assertEquals("2017-06-29T19:53:56", result.getCreatedAt().toString());
    }
}
