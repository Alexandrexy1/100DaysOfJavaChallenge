package com.example.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotEnvConfiguration {
    @Bean
    Dotenv dotenv() {
        return Dotenv.configure().ignoreIfMissing().load();
    }
}
