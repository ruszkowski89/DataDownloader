package com.ruszkowski.datadownloader.externalapiconfig;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ExternalApiConfig {
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private ExternalApiConfigType type;
    private String url;
}
