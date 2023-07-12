package com.ruszkowski.datadownloader.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruszkowski.datadownloader.externalapiconfig.ConfigNotFoundException;
import com.ruszkowski.datadownloader.externalapiconfig.ExternalApiConfigService;
import com.ruszkowski.datadownloader.externalapiconfig.ExternalApiConfigType;
import com.ruszkowski.datadownloader.httprequestexecutor.HttpRequestExecutionException;
import com.ruszkowski.datadownloader.httprequestexecutor.HttpRequestExecutor;
import com.ruszkowski.datadownloader.user.loginstatistics.LoginStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UserService {

    private final LoginStatisticsService loginStatsService;
    private final ExternalApiConfigService apiService;
    private final HttpRequestExecutor requestExecutor;

    @Autowired
    public UserService(LoginStatisticsService loginStatsService, ExternalApiConfigService apiService,
                       HttpRequestExecutor requestExecutor) {
        this.loginStatsService = loginStatsService;
        this.apiService = apiService;
        this.requestExecutor = requestExecutor;
    }

    public UserDto download(String parameter) throws JsonProcessingException, HttpRequestExecutionException,
            ConfigNotFoundException, MalformedURLException, URISyntaxException {
        URI uri = apiService.generateUrl(ExternalApiConfigType.GITHUB, parameter);
        String jsonResponse = requestExecutor.downloadData(uri);
        UserDto dto = UserMapper.toDto(jsonResponse);

        loginStatsService.updateLoginStatistics(dto.getLogin());
        return dto;
    }

    /*
    *      calculations = 6 / followers * (2 + public_repos)
    */
    static BigDecimal getCalculationsField(long followers, long publicRepos) {
        BigDecimal result;
        try {
            BigDecimal six = BigDecimal.valueOf(6);
            BigDecimal followersNum = BigDecimal.valueOf(followers);
            BigDecimal twoPlusPublicRepos = BigDecimal.valueOf(2 + publicRepos);

            result = six.divide(followersNum, 2, RoundingMode.UP)
                        .multiply(twoPlusPublicRepos);
        } catch (ArithmeticException e) {
            result = BigDecimal.ZERO;
        }

        return result;
    }
}
