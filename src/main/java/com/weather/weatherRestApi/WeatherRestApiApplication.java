package com.weather.weatherRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WeatherRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherRestApiApplication.class, args);
	}

}
