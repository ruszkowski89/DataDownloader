package com.ruszkowski.datadownloader.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private long id;
    private String login;
    private String name;
    private String type;
    private URL avatarUrl;
    private LocalDateTime createdAt;
    private BigDecimal calculations;

}
