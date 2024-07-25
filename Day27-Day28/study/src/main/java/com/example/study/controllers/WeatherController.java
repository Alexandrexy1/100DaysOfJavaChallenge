package com.example.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.model.WeatherResponse;
import com.example.study.services.WeatherService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/weather")
@RestController
public class WeatherController {
    
    @Autowired WeatherService weatherService;

    @GetMapping
    public Mono<WeatherResponse> getWeather(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
