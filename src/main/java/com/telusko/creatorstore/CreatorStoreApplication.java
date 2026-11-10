package com.telusko.creatorstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class CreatorStoreApplication {

    static {
        // Postgres rejects the legacy "Asia/Calcutta" zone ID that Java reports on this machine
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach((entry) -> System.setProperty(
                entry.getKey(), entry.getValue()
        ));
        SpringApplication.run(CreatorStoreApplication.class, args);
    }

}
