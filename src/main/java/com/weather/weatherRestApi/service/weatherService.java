package com.weather.weatherRestApi.service;

import com.weather.weatherRestApi.model.WeatherMetrics;
import com.weather.weatherRestApi.model.WeatherMetricsStats;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface weatherService {

    public List<WeatherMetrics> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);

    public WeatherMetrics addWeatherMetrics(WeatherMetrics weather);

    public List<WeatherMetrics> findByDateAndSensor(LocalDate startDate, LocalDate endDate, String sensorName);

    public List<WeatherMetricsStats> findStatsByDateAndSensor(LocalDate startDate, LocalDate endDate, String sensorName);

    public List<WeatherMetrics> find();
}
