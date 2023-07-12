package com.ruszkowski.datadownloader.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class UserMapper {

    public static final String PUBLIC_REPOS = "public_repos";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String AVATAR_URL = "avatar_url";
    public static final String CREATED_AT = "created_at";
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String FOLLOWERS = "followers";



    public static UserDto toDto(String json) throws JsonProcessingException, URISyntaxException, MalformedURLException {
        UserDto dto = objectMapper.readValue(json, UserDto.class);
        JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);

        // might be better idea to create fieldMappings in ExternalApiConfigs and store them in DB
        URL avatarUrl = new URI(jsonNode.get(AVATAR_URL).asText()).toURL();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime createdAt = LocalDateTime.parse(jsonNode.get(CREATED_AT).asText(), format);
        BigDecimal calculations = UserService.getCalculationsField(jsonNode.get(FOLLOWERS).asLong(),
                                                                   jsonNode.get(PUBLIC_REPOS).asLong());

        dto.setAvatarUrl(avatarUrl);
        dto.setCreatedAt(createdAt);
        dto.setCalculations(calculations);

        return dto;
    }
}
