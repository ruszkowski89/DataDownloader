package com.ruszkowski.datadownloader.externalapiconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalApiConfigRepository extends JpaRepository<ExternalApiConfig, Long> {

    Optional<ExternalApiConfig> findByType(ExternalApiConfigType type);
}
