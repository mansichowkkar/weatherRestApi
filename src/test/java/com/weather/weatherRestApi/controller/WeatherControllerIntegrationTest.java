package com.weather.weatherRestApi.controller;

import com.weather.weatherRestApi.model.WeatherMetrics;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherControllerIntegrationTest {

    @Test
    @Order(1)
    void saveWeatherDataTest(){

        TestRestTemplate restTemplate = new TestRestTemplate();
        WeatherMetrics weatherMetrics = new WeatherMetrics();
        weatherMetrics.setDate(LocalDate.of(2022, 1, 1));
        weatherMetrics.setHumidity(20);
        weatherMetrics.setId(200);
        weatherMetrics.setSensor("sensor1");
        weatherMetrics.setTemperature(22);
        weatherMetrics.setWindVelocity(30);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WeatherMetrics> request = new HttpEntity<WeatherMetrics>(weatherMetrics, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/weather/temperature",request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @Order(2)
    void getWeatherReportByDatesTest(){

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/weather/temperature/2023-06-28/2023-06-28", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @Order(3)
    void getWeatherDataTest(){

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/weather/temperature", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }



}
