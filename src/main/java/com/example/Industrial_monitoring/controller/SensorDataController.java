package com.example.Industrial_monitoring.controller;

import com.example.Industrial_monitoring.model.SensorData;
import com.example.Industrial_monitoring.repository.SensorDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorDataController {

    private SensorDataRepository repo;

    public SensorDataController(SensorDataRepository repo){
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<SensorData> getAllData(){
        return repo.findAll();
    }
}
