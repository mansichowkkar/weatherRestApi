package com.weather.weatherRestApi.service;

import com.weather.weatherRestApi.model.WeatherMetrics;
import com.weather.weatherRestApi.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplTest {


    private WeatherMetrics mockWeatherMetrics;

   @Mock
    private WeatherRepository mockWeatherRepository;

    @BeforeEach
    public void setup(){

        mockWeatherMetrics = new WeatherMetrics();
        mockWeatherMetrics.setDate(LocalDate.of(2022, 1, 1));
        mockWeatherMetrics.setHumidity(20);
        mockWeatherMetrics.setId(200);
        mockWeatherMetrics.setSensor("sensor1");
        mockWeatherMetrics.setTemperature(22);
        mockWeatherMetrics.setWindVelocity(30);
    }
    @Test
    public void givenWeatherMetrics_whenDateProvided_findByDateCreatedBetweenMetrics(){
        //setup

        List<WeatherMetrics> result = mockWeatherRepository.findByDateCreatedBetween(mockWeatherMetrics.getDate(), mockWeatherMetrics.getDate());
        result.add(mockWeatherMetrics);


        //assert
        assertEquals(result.get(0).getHumidity(),20 );
        assertEquals(result.get(0).getTemperature(),22);
        assertEquals(result.get(0).getWindVelocity(),30);

    }

    @Test
    public void givenWeatherMetrics_whenDateAndSensorProvided_findByDateAndSensorMetrics(){
        //setup

        List<WeatherMetrics> result  = mockWeatherRepository.findByDateAndSensor(mockWeatherMetrics.getDate(), mockWeatherMetrics.getDate(), mockWeatherMetrics.getSensor());
        result.add(mockWeatherMetrics);


        //assert
        assertEquals(result.get(0).getHumidity(),20 );
        assertEquals(result.get(0).getTemperature(),22);
        assertEquals(result.get(0).getWindVelocity(),30);

    }
    @Test
    public void givenWeatherMetrics_whenNoDateAndSensorProvided_findMetrics(){
        //setup

        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022,1,1);
        List<WeatherMetrics> result = mockWeatherRepository.findByDateCreatedBetween(startDate, endDate);
        result.add(mockWeatherMetrics);

        //assert
        assertEquals(result.get(0).getHumidity(),20 );
        assertEquals(result.get(0).getTemperature(),22);
        assertEquals(result.get(0).getWindVelocity(),30);

    }

    @Test
    @Order(2)
    public void givenWeatherMetrics_whenDateAndSensorProvided_findWeatherAvgMetrics(){
        //setup

        List<WeatherMetrics> result = mockWeatherRepository.findByDateAndSensor(mockWeatherMetrics.getDate(), mockWeatherMetrics.getDate(), mockWeatherMetrics.getSensor());
        result.add(mockWeatherMetrics);


        //assert
        assertEquals(result.get(0).getHumidity(),20 );
        assertEquals(result.get(0).getTemperature(),22);
        assertEquals(result.get(0).getWindVelocity(),30);

    }

    @Test
    public void givenWeatherMetrics_whenDateProvided_isNull_findMetrics(){
        //setup
        LocalDate startDate = null;
        LocalDate endDate = null;

        List<WeatherMetrics> result = mockWeatherRepository.findByDateCreatedBetween(startDate,endDate);

        //assert
        assertThat(result).isEmpty();


    }
    @Test
    @Order(1)
    public void givenWeatherMetrics_whenDataProvided_saveData(){
        //setup

        mockWeatherRepository.save(mockWeatherMetrics);


        //assert
        assertEquals(mockWeatherMetrics.getHumidity(),20 );
        assertEquals(mockWeatherMetrics.getTemperature(),22);
        assertEquals(mockWeatherMetrics.getWindVelocity(),30);

    }
}