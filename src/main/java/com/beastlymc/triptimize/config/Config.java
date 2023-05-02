package com.beastlymc.triptimize.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure().filename("database.env").load();

    public static String get(String key) {
        return dotenv.get(key);
    }

}
