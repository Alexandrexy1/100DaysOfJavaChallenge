package com.example.study.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.example.study.model.WeatherResponse;

import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    
    private final WebClient.Builder webClientBuilder;
    private final String apiUrl;
    private final String apiKey;

    

    public WeatherService(Builder webClientBuilder, String apiUrl, String apiKey) {
        this.webClientBuilder = webClientBuilder;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
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

    public  Mono<WeatherResponse> getWeather(String city) {
        return webClientBuilder.build().get().uri(uriBuilder -> uriBuilder
            .path(apiUrl)
            .queryParam("q", city)
            .queryParam("appid", apiKey)
            .queryParam("units", "metric")
            .build())
            .retrieve()
            .bodyToMono(WeatherResponse.class);
    }
    
}
