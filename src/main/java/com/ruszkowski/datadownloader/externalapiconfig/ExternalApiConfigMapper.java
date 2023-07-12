package com.ruszkowski.datadownloader.externalapiconfig;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExternalApiConfigMapper {

    public static ExternalApiConfig toEntity(ExternalApiConfigDto dto) {
        ExternalApiConfig entity = new ExternalApiConfig();
        entity.setUrl(dto.getUrl());
        entity.setType(dto.getType());
        return entity;
    }

    public static ExternalApiConfigDto toDto(ExternalApiConfig entity) {
        ExternalApiConfigDto dto = new ExternalApiConfigDto();
        dto.setUrl(entity.getUrl());
        dto.setType(entity.getType());
        return dto;
    }
}
