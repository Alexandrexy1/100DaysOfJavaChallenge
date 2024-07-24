package com.example.study.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    
    @JsonProperty("main")
    private Main main;

    @JsonProperty("name")
    private String cityName;

    public Main getMain() {
        return main;
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
}
