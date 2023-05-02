package com.beastlymc.triptimize.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
@Data
public class DatabaseProperties {
    private String url;
    private String username;
    private String password;
}
