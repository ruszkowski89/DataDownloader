package com.ruszkowski.datadownloader.externalapiconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "external-apis")
public class ExternalApiConfigController {
    private final ExternalApiConfigService service;

    @Autowired
    public ExternalApiConfigController(ExternalApiConfigService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ExternalApiConfigDto externalApiConfigDto) {
        return ResponseEntity.ok(service.create(externalApiConfigDto));
    }

}
