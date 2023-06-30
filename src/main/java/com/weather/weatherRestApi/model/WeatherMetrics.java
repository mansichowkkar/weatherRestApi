package com.weather.weatherRestApi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Weather_Metrics")
public class WeatherMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @CreatedDate
    @Column(name="date",updatable = false,nullable = false)
    private LocalDate date;

    @Column(name="humidity")
    private Integer humidity;

    @Column(name = "temperature")
    private Integer temperature;

    @NotNull
    @Column(name = "sensor")
    private String sensor;

    @Column(name = "windVelocity")
    private Integer windVelocity;
}
