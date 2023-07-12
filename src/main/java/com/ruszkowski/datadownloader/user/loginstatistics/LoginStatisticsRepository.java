package com.ruszkowski.datadownloader.user.loginstatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginStatisticsRepository extends JpaRepository<LoginStatistics, Long> {

    Optional<LoginStatistics> findByLogin(String login);
}
