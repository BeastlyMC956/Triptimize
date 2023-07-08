package com.beastlymc.triptimize.util;

import java.sql.Date;

public final class Util {
    public static final String DEFAULT_API_PATH = "api/v1/";
    public static final String PUBLIC_API_PATH = "api/v1/public/";

    public static Date getCurrentSQLDate() {
        java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
        return new Date(currentDate.getTime());
    }

    private Util() { }
}
