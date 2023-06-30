package com.weather.weatherRestApi.repository;

import com.weather.weatherRestApi.model.WeatherMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherMetrics,Integer> {

    //TODO implement this class so that sql can be injected and maintain in different files

    @Query(value = "select * from weather_metrics where date BETWEEN :startDate and :endDate",nativeQuery = true)
    List<WeatherMetrics> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = "select * from weather_metrics where sensor= :sensorName and date BETWEEN :startDate and :endDate",nativeQuery = true)
    List<WeatherMetrics> findByDateAndSensor(LocalDate startDate, LocalDate endDate, String sensorName);


}
