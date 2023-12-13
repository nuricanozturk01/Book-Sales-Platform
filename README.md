# Book-Sales-Platform

# Project Architecture Overview

## Introduction

This document provides an overview of the architecture for a microservices-based system. The system is designed to
handle various services such as user management, book management, order processing, payment processing, and
notifications. The architecture leverages Spring Boot for the microservices framework, WebSocket for real-time
client-server communication, and Apache Kafka for handling message streams between services.

## System Architecture Diagram

![diagram](https://github.com/nuricanozturk01/Book-Sales-Platform/assets/62218588/55e75632-2b7b-4626-bd59-f76f0e190a4b)

## Demo Video
### Notification
https://github.com/nuricanozturk01/Book-Sales-Platform/assets/62218588/fda3b0b6-c0c4-48c0-9379-30685e779bf7
### UI
https://github.com/nuricanozturk01/Book-Sales-Platform/assets/62218588/797956fc-8be9-4f84-a757-163de4a52996

## Components Description

### API Gateway

- Serves as the entry point for clients.
- Routes requests to appropriate microservices.
- Can be implemented using Spring Cloud Gateway.

### Discovery Service

- Manages service instances and routing.
- Typically implemented with Eureka or Consul.

### User Management

- Handles user information and authentication.
- Interacts with User DB (PostgreSQL).
- Implemented using Spring Boot with Spring Security.

### Book Service

- Manages book information and inventory.
- Interacts with Book DB (PostgreSQL).
- Implemented with Spring Boot.

### Order Service

- Processes book orders.
- Interacts with Order DB (PostgreSQL).
- Implemented using Spring Boot and may use Spring Cloud Stream with Kafka.

### Stock Service

- Manages stock levels of books.
- Interacts with Stock DB (MongoDB).
- Implemented using Spring Boot and communicates via Kafka topics.

### Payment Service

- Processes payments for orders.
- Interacts with Payment DB (PostgreSQL).
- Uses Spring Boot and Kafka for transaction events.

### Notification Service

- Sends notifications to users about various events such as order status, payment confirmations, etc.
- Interacts with Notification DB (MongoDB).
- Implemented with Spring Boot and uses Kafka for receiving notification triggers.

### Log Service

- Centralized logging service for all microservices.
- implemented for log simulation.

### Kafka Broker

- Apache Kafka used as the messaging backbone.
- Different topics are defined for various event types.

### WebSocket

- Allows for real-time communication between the server and clients.
- Can be implemented using STOMP over WebSocket with Spring Boot.

## Technologies Used

- **Java 17**
- **Spring Framework 6**:
- **Spring Boot 3.1.6**: Primary framework for creating microservices.
- **WebSocket**: For real-time bi-directional communication between client and server.
- **Apache Kafka**: For handling distributed streaming of messages.
- **PostgreSQL**: For relational databases.
- **MongoDB**: For non-relational databases.
- **Apache Kafka**: For Message Broker.
- **Spring Cloud Stream**: For building highly scalable event-driven microservices connected with shared messaging
  systems.
- **Spring Cloud Gateway**: For API Gateway.
- **Eureka Server**: For Service Discovery.
- **Angular**: For Frontend.

## Running the Services

Instructions on how to run and deploy individual services should be provided, detailing any specific configurations
required for Spring Boot applications, WebSocket setup, and Kafka topic initialization.

## Conclusion

This architecture is designed to be scalable, resilient, and flexible, capable of handling high-volume data streams and
real-time communication with clients. By using Spring Boot, WebSocket, and Apache Kafka, the system ensures a robust
backend capable of managing a complex flow of data across various services and databases.

# Important Notes

 - This project was made to learn Apache Kafka. Most details other than Kafka have been overlooked (Example: security,
etc..).
In this project, the basic features of Kafka have been used. A simple log tracking system that will support Kafka has
been made.
The sole purpose of the Log Tracking system is to send the data coming from Kafka to Angular via websocket and display
the logs in real time.
 - The project is not complete. It is still in development. The project will be completed in the future.
 - The project is not suitable for production. It is not recommended to use it in production.
 - ### I will update the project when I learnt new things about Apache Kafka, Websocket, Reactive Programming, etc..