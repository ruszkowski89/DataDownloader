package com.ruszkowski.datadownloader.externalapiconfig;

public class ConfigNotFoundException extends Exception {
    public ConfigNotFoundException(ExternalApiConfigType type) {
        super(String.format("Sorry, config for API %s not found.", type.name()));
    }
}
