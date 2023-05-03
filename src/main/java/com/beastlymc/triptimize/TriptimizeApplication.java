package com.beastlymc.triptimize;

import com.beastlymc.triptimize.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TriptimizeApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TriptimizeApplication.class);

        application.run(args);
    }

}
