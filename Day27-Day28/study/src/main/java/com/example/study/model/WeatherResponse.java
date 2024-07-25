package com.example.study.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    
    @JsonProperty("main")
    private Main main;

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("weather")
    private List<Weather> weather;


    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getCityName() {
        return cityName;
    }

    public static class Main {
        @JsonProperty("temp")
        private double temp;

        public double getTemp() {
            return temp;
        }   
    }

    public static class Weather {
        @JsonProperty("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}