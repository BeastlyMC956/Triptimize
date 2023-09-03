package com.beastlymc.triptimize.util;

import java.sql.Timestamp;

public final class Util {

    public static final String DEFAULT_API_PATH = "api/v1/";
    public static final String PUBLIC_API_PATH = "api/v1/public/";

    public static Timestamp getCurrentSQLDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    private Util() {
    }
}
