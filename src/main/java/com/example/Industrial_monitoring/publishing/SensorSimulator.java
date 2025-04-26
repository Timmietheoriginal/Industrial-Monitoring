package com.example.Industrial_monitoring.publishing;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorSimulator{

    private final MqttClient mqttClient;
    private final Random random = new Random();
    private final String topic = "sensor/data";

    public SensorSimulator(MqttClient mqttClient){
        this.mqttClient = mqttClient;
    }

    @Scheduled(fixedRate = 5000)
    public void simulateSensorData(){
        try{
            double temp = 50 + (110-50) * random.nextDouble();
            String payload = String.format("%.2f", temp);
            MqttMessage message = new MqttMessage(payload.getBytes());

            if(mqttClient.isConnected()){
                mqttClient.publish(topic, message);
                System.out.println("Published: " + payload);
            } else{
                System.out.println("Mqtt client not connected. Skipping Publishing");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
