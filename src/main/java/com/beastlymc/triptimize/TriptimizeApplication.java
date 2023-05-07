package com.beastlymc.triptimize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main class of the Triptimize application.
 */
@SpringBootApplication
@RestController
public class TriptimizeApplication {

    /**
     * The main method of the Triptimize application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TriptimizeApplication.class);

        application.run(args);
    }

}
