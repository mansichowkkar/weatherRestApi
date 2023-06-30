package com.weather.weatherRestApi.model;

import com.sun.istack.NotNull;
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

@Entity
@Getter
@Setter
@Table(name = "Weather_Metrics_Stats")
public class WeatherMetricsStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @CreatedDate
    @Column(name="startDate",updatable = false,nullable = false)
    private LocalDate startDate;

    @CreatedDate
    @Column(name="endDate",updatable = false,nullable = false)
    private LocalDate endDate;

    @Column(name="avgHumidity")
    private long avgHumidity;

    @Column(name = "avgTemperature")
    private long avgTemperature;

    @NotNull
    @Column(name = "sensor")
    private String sensor;


}
