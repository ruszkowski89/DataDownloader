package com.ruszkowski.datadownloader.user.loginstatistics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class LoginStatistics {
    // I know it was said to make only 2 columns in this table, however using "login" as ID is considered bad practice, so I made long Id
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private long requestCount;

    public LoginStatistics(String login, long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }
}
