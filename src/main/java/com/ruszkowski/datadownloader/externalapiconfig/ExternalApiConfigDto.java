package com.ruszkowski.datadownloader.externalapiconfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiConfigDto {
    private ExternalApiConfigType type;
    private String url;
}
