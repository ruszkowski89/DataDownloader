package com.ruszkowski.datadownloader.externalapiconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ExternalApiConfigService {
    private final ExternalApiConfigRepository repo;

    @Autowired
    public ExternalApiConfigService(ExternalApiConfigRepository repo) {
        this.repo = repo;
    }

    public ExternalApiConfigDto create(ExternalApiConfigDto dto) {
        ExternalApiConfig entityToSave = ExternalApiConfigMapper.toEntity(dto);
        ExternalApiConfig saved = repo.save(entityToSave);
        return ExternalApiConfigMapper.toDto(saved);
    }

    public URI generateUrl(ExternalApiConfigType type, String paramValue) throws ConfigNotFoundException {
        ExternalApiConfig externalApiConfig = repo.findByType(type)
                .orElseThrow(() -> new ConfigNotFoundException(type));
        String urlWithPlaceholder = externalApiConfig.getUrl();
        String replaced = urlWithPlaceholder.replace("{parameter}", String.valueOf(paramValue));
        return URI.create(replaced);
    }

}
