package com.example.Industrial_monitoring.mqtt;

import com.example.Industrial_monitoring.model.SensorData;
import com.example.Industrial_monitoring.repository.SensorDataRepository;
import com.example.Industrial_monitoring.service.EmailService;
import com.example.Industrial_monitoring.websocket.WebSocketAlertHandler;
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
    private WebSocketAlertHandler alertHandler;
    //private EmailService emailService;


    @Value("${mqtt.topic}")
    private String topic;

    public MQTTSubscriber(MqttClient mqttClient, SensorDataRepository repo,
                          WebSocketAlertHandler alertHandler, EmailService emailService){
        this.mqttClient = mqttClient;
        this.repo = repo;
        this.alertHandler = alertHandler;
        //this.emailService = emailService;
    }

    @PostConstruct
    public void subscribe() throws MqttException{
        System.out.println("Subscribing to MQTT topic: " + topic);


        mqttClient.subscribe(topic, (t, msg) -> {
            String payload = new String(msg.getPayload());
            System.out.println("Received: " + payload);


            double temp = Double.parseDouble(payload);
            boolean isAlert = temp > 90;

            if(isAlert){
                String alertMessage = "🚨 ALERT: High temperature detected: " + temp + "°C";
                System.out.println(alertMessage);

                try{
                    alertHandler.sendAlert(alertMessage);
                } catch(Exception e){
                    System.err.println("Failed to send Websocket alert: " + e.getMessage());
                    e.printStackTrace();
                }


                // email service providers are all silly
                //try{
                  //  String subject = "High Temperature Alert";
                    //String body = "Warning! The Temperature has exceeded the safe limit. Current Temperature: " + temp + "°C";
                    //emailService.sendEmail("funkesogunle01@gmail.com", subject, body);
                //} catch (Exception e){
                  //  System.err.println("Failed to send email alert: " + e.getMessage());
                    //e.printStackTrace();
                //}
            }

            SensorData data = new SensorData(payload, isAlert, LocalDateTime.now());
            repo.save(data);
        });
    }
}
