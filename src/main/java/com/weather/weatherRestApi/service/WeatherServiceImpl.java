package com.weather.weatherRestApi.service;

import com.weather.weatherRestApi.exception.ResourceNotFoundException;
import com.weather.weatherRestApi.model.WeatherMetrics;
import com.weather.weatherRestApi.model.WeatherMetricsStats;
import com.weather.weatherRestApi.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
public class WeatherServiceImpl implements weatherService {

    @Autowired
    private WeatherRepository weatherRepository;
    private Logger log;


    @Autowired
    public void setTemperatureRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<WeatherMetrics> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {

        try {
            if (startDate.isEqual(null)) {
                startDate = new java.sql.Date(new java.util.Date().getTime())
                        .toLocalDate();
                ;
            } else if (endDate.isEqual(null)) {
                endDate = new java.sql.Date(new java.util.Date().getTime())
                        .toLocalDate();
                ;
            }
            return weatherRepository.findByDateCreatedBetween(startDate, endDate);

        } catch (Exception e) {
            log.error("eror parsing date");
            throw new ResourceNotFoundException("error finding data for the given dates", e);
        }


    }

    @Override
    public WeatherMetrics addWeatherMetrics(WeatherMetrics weatherMetrics) {
        try {

            return weatherRepository.save(weatherMetrics);
        } catch (Exception e) {
            throw new ResourceNotFoundException("error saving given data in weather_Metrics table", e);
        }

    }

    @Override
    public List<WeatherMetrics> findByDateAndSensor(LocalDate startDate, LocalDate endDate, String sensorName) {

        try {
            if (startDate.equals(null)) {
                startDate = new java.sql.Date(new java.util.Date().getTime())
                        .toLocalDate();
                ;
            } else if (endDate.equals(null)) {
                endDate = new java.sql.Date(new java.util.Date().getTime())
                        .toLocalDate();
                ;
            }
            if (sensorName.equals(null) || sensorName.isEmpty()) {
                sensorName = "sensor1";
            }
            return weatherRepository.findByDateAndSensor(startDate, endDate, sensorName);

        } catch (Exception e) {
            throw new ResourceNotFoundException("error finding data for the given dates and sensor name", e);
        }
    }


    @Override
    public List<WeatherMetrics> find() {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = new java.sql.Date(new java.util.Date().getTime())
                    .toLocalDate();
            endDate = new java.sql.Date(new java.util.Date().getTime())
                    .toLocalDate();

            return weatherRepository.findByDateCreatedBetween(startDate, endDate);

        } catch (Exception e) {
            log.error("eror parsing date");
            throw new ResourceNotFoundException("error finding data for the given dates", e);
        }

    }

    @Override
    public List<WeatherMetricsStats> findStatsByDateAndSensor(LocalDate startDate, LocalDate endDate, String sensorName) {

        try {
            WeatherMetricsStats weatherMetricsStats = new WeatherMetricsStats();
            List<WeatherMetricsStats>  resultWeatherStats= new ArrayList<>();
            List<WeatherMetrics>  weatherStats= new ArrayList<>();
            weatherStats =  weatherRepository.findByDateAndSensor(startDate, endDate, sensorName);

            long metricsTemp = 0 ;
            long metricsHumidity = 0;
            long daysBetween = DAYS.between(startDate, endDate);

            for (WeatherMetrics metrics : weatherStats) {
                metricsTemp += metrics.getTemperature();
                metricsHumidity += metrics.getHumidity();

            }
            // TODO: handle divide by 0 exception here
            metricsTemp = metricsTemp/daysBetween;
            metricsHumidity = metricsHumidity/daysBetween;

            weatherMetricsStats.setAvgHumidity(metricsHumidity);
            weatherMetricsStats.setSensor(sensorName);
            weatherMetricsStats.setEndDate(endDate);
            weatherMetricsStats.setStartDate(startDate);
            weatherMetricsStats.setId(100);
            weatherMetricsStats.setAvgTemperature(metricsTemp);

            resultWeatherStats.add(weatherMetricsStats);


            return resultWeatherStats;

        } catch (Exception e) {
            throw new ResourceNotFoundException("error finding data for the given dates and sensor name", e);
        }

    }
}
