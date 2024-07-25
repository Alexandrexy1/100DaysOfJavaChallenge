package com.example.study.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.study.model.WeatherResponse;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    
    private final WebClient.Builder webClientBuilder;
    private final Dotenv dotenv;
    private String apiUrl;
    private String apiKey;

    public WeatherService(Builder webClientBuilder, Dotenv dotenv) {
        this.webClientBuilder = webClientBuilder;
        this.dotenv = dotenv;
    }
    
    public  Mono<WeatherResponse> getWeather(String city) {
        apiUrl = dotenv.get("OPENWEATHERMAP_API_URL");
        apiKey = dotenv.get("OPENWEATHERMAP_API_KEY");

        String uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
            .queryParam("q", city)
            .queryParam("appid", apiKey)
            .queryParam("units", "metric")
            .queryParam("lang", "pt-br")
            .toUriString();
        return webClientBuilder.build().get().uri(uri).retrieve().bodyToMono(WeatherResponse.class);
    } 

    public WebClient.Builder getWebClientBuilder() {
        return webClientBuilder;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
    
    public Dotenv getDotenv() {
        return dotenv;
    }
}
