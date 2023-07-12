package com.ruszkowski.datadownloader.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruszkowski.datadownloader.externalapiconfig.ConfigNotFoundException;
import com.ruszkowski.datadownloader.httprequestexecutor.HttpRequestExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "{login}")
    public ResponseEntity getUser(@PathVariable("login") String login) {
        try {
            return ResponseEntity.ok(service.download(login));
        } catch (HttpRequestExecutionException | JsonProcessingException | MalformedURLException | URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ConfigNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
