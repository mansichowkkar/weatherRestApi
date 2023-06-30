package com.weather.weatherRestApi.controller;

import com.weather.weatherRestApi.exception.CustomNotFoundException;
import com.weather.weatherRestApi.exception.ResourceNotFoundException;
import com.weather.weatherRestApi.model.WeatherMetrics;
import com.weather.weatherRestApi.model.WeatherMetricsStats;
import com.weather.weatherRestApi.service.WeatherServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
@NoArgsConstructor
@Slf4j
public class WeatherController {

    private WeatherServiceImpl weatherService;

    private Logger log;

    @Autowired
    public WeatherController(WeatherServiceImpl weather) {

        this.weatherService = weather;
    }


    @GetMapping("/temperature/{startDate}/{endDate}")
    public ResponseEntity<List<WeatherMetrics>> getWeatherReportByDates(
            @PathVariable("startDate")
            String startDate,
            @PathVariable("endDate")
            String endDate
    ) {
        try{
            LocalDate dateBegin = LocalDate.parse(startDate);
            LocalDate dateEnd = LocalDate.parse(endDate);

            return new ResponseEntity<>(weatherService.findByDateCreatedBetween(dateBegin, dateEnd), HttpStatus.OK);
        }catch (Exception e){
            log.error("failed to parse dates provided");
            throw new ResourceNotFoundException("data not found for given dates :", e);
        }

    }

    @GetMapping("/temperature/{startDate}/{endDate}/sensor/{sensor}")
    public ResponseEntity<List<WeatherMetrics>> getWeatherReportByDatesAndSensor(
            @PathVariable("startDate")
            String startDate,
            @PathVariable("endDate")
            String endDate,
            @PathVariable("sensor")
            String sensor
    ){
        try{
            LocalDate dateBegin = LocalDate.parse(startDate);
            LocalDate dateEnd = LocalDate.parse(endDate);

            return new ResponseEntity<>(weatherService.findByDateAndSensor(dateBegin, dateEnd, sensor), HttpStatus.OK);
        }catch (Exception e){
            log.error("failed to parse dates provided");
            throw new ResourceNotFoundException("data not found for given dates :", e);
        }


    }

    @PostMapping("/temperature")
    public ResponseEntity<WeatherMetrics> saveWeatherData(
            @RequestBody
            WeatherMetrics temperature
    )throws ResourceNotFoundException {

        return new ResponseEntity<>(weatherService.addWeatherMetrics(temperature), HttpStatus.OK);
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<WeatherMetrics>> getWeatherReport( ) {
        try{
            return new ResponseEntity<>(weatherService.find(), HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException("data not found :", e);
        }
    }

    @GetMapping("/temperature/{startDate}/{endDate}/sensor/{sensor}/stats")
    public ResponseEntity<List<WeatherMetricsStats>> getWeatherStatsByDatesAndSensor(
            @PathVariable("startDate")
            String startDate,
            @PathVariable("endDate")
            String endDate,
            @PathVariable("sensor")
            String sensor
    ){
        try{
            LocalDate dateBegin = LocalDate.parse(startDate);
            LocalDate dateEnd = LocalDate.parse(endDate);

            return new ResponseEntity<>(weatherService.findStatsByDateAndSensor(dateBegin, dateEnd, sensor), HttpStatus.OK);
        }catch (Exception e){
            log.error("failed to parse dates provided");
            throw new ResourceNotFoundException("data not found for given dates :", e);
        }


    }

}
