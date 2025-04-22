package com.example.Industrial_monitoring;

import com.example.Industrial_monitoring.model.SensorData;
import com.example.Industrial_monitoring.repository.SensorDataRepository;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MQTTSubscriber {

    private MqttClient mqttClient;
    private SensorDataRepository repo;


    @Value("${mqtt.topic}")
    private String topic;

    public MQTTSubscriber(MqttClient mqttClient, SensorDataRepository repo){
        this.mqttClient = mqttClient;
        this.repo = repo;
    }

    @PostConstruct
    public void subscribe() throws MqttException{
        System.out.println("Subscribing to MQTT topic: " + topic);
        mqttClient.subscribe(topic, (t, msg) -> {
            String payload = new String(msg.getPayload());
            System.out.println("Received: " + payload);

            SensorData data = new SensorData(payload, LocalDateTime.now());
            repo.save(data);
        });
    }
}
