package com.weather.weatherRestApi.controller;

import com.weather.weatherRestApi.model.WeatherMetrics;
import com.weather.weatherRestApi.repository.WeatherRepository;
import com.weather.weatherRestApi.service.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @InjectMocks
    private WeatherMetrics mockWeatherMetrics;

    @Mock
    private  WeatherRepository mockWeatherRepository;
    @Mock
    private WeatherServiceImpl mockWeatherService;

    @BeforeEach
    public void setup(){
        mockWeatherService.setTemperatureRepository(mockWeatherRepository);
        mockWeatherMetrics = new WeatherMetrics();
        mockWeatherMetrics.setDate(LocalDate.of(2022, 1, 1));
        mockWeatherMetrics.setHumidity(20);
        mockWeatherMetrics.setId(200);
        mockWeatherMetrics.setSensor("sensor1");
        mockWeatherMetrics.setTemperature(22);
        mockWeatherMetrics.setWindVelocity(30);

    }

    @Test
    public void givenWeatherMetrics_whenDateProvided_getWeatherReportByDates(){
        //setup
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022,1,1);

        List<WeatherMetrics> result = mockWeatherService.findByDateCreatedBetween(startDate, endDate);
        result.add(mockWeatherMetrics);

        //assert
        assertEquals(result.get(0).getHumidity(),20 );
        assertEquals(result.get(0).getTemperature(),22);
        assertEquals(result.get(0).getWindVelocity(),30);

    }

}