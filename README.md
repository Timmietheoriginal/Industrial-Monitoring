# 🏠 Industrial Monitoring System

A real-time industrial monitoring system built with Spring Boot, MQTT, WebSocket, JWT, and PostgreSQL. This system ingests sensor data over MQTT, stores it in a database, provides REST APIs, and supports real-time dashboards and secured endpoints.

---

## 🚀 Features

✅ Subscribes to MQTT sensor data
✅ Stores sensor data in PostgreSQL
✅ Simulates real-time sensor data
✅ REST API for sensor history and status
✅ WebSocket support for real-time alerts**
✅ JWT-based authentication for secured endpoints**
⚙️ Ready for dashboard integration

---

## 🛠 Tech Stack

* Java 21+
* Spring Boot
* MQTT (Mosquitto)
* WebSocket (Spring-native)
* PostgreSQL
* Spring Security + JWT
* IntelliJ IDEA

---

## 📦 How to Run

1. **Install Mosquitto MQTT broker**
   [Mosquitto installation guide](https://mosquitto.org/download/)

2. **Configure PostgreSQL database**

    * Create a database (e.g., `monitoring_db`)
    * Update `application.properties` or `application.yml` with DB credentials

3. **Start the Spring Boot application**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Simulate sensor data**

    * Either via internal simulator (included)
    * Or use external tools like MQTT Explorer or `mosquitto_pub`

5. **Access REST APIs**

    * Public endpoints: View sensor data
    * Secured endpoints (e.g., POST sensor data): Require JWT token
    * Authenticate via `/auth/login` and use the returned token

6. **WebSocket Dashboard (optional frontend)**
   Connect to `/ws/alerts` to receive real-time sensor alerts (e.g., high temperature)

---

## 🔐 Authentication

* Use `/auth/register` and `/auth/login` to create and authenticate users
* Attach the JWT token to `Authorization: Bearer <token>` for secured endpoints

---

## 📈 To Do Next

* 🐳 Dockerize the application for production use
* 📊 Build a real-time dashboard (React/Vue/Angular)
* 📉 Historical data charting and analytics

---

## 📁 Folder Structure (Overview)

```
src/
👤 config/             # Security config, WebSocket config
📄 controller/         # REST & WebSocket endpoints
📄 dto/                # Data Transfer Object
📄 model/              # JPA entities and DTOs
📄 repository/         # Data access layers
📄 service/            # Business logic
📄 mqtt/               # MQTT subscriber and simulator
📄 websocket/          # WebSocket handlers
```

---

## 🧑‍💻 Author

Made with ❤️ by [@Timmietheoriginal](https://github.com/Timmietheoriginal)
