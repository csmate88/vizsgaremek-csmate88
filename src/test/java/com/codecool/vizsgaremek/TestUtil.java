package com.codecool.vizsgaremek;

import org.flywaydb.core.Flyway;

public class TestUtil {
    private static final Flyway flyway=Flyway.configure().envVars().load();

    public static void resetDatabase(){
        flyway.clean();
        flyway.migrate();
    }
}
