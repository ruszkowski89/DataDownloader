package com.ruszkowski.datadownloader.user.loginstatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginStatisticsService {

    private final LoginStatisticsRepository loginStatsRepo;

    @Autowired
    public LoginStatisticsService(LoginStatisticsRepository repo) {
        this.loginStatsRepo = repo;
    }

    public void updateLoginStatistics(String login) {
        loginStatsRepo.findByLogin(login)
                .ifPresentOrElse(
                        this::updateRequestCount,
                        () -> loginStatsRepo.save(new LoginStatistics(login, 1))
                );
    }

    private void updateRequestCount(LoginStatistics l) {
        l.setRequestCount(l.getRequestCount()+1);
        loginStatsRepo.save(l);
    }
}
