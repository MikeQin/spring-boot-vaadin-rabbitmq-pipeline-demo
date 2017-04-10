# Spring-Boot, Vaadin, RabbitMQ, Data Pipeline Demo

## Overview

### This Demo App Showcases the following Latest Technology Stack:

  * Spring AMQP Client
    - RabbitTemplate
    - SimpleMessageListenerContainer
    - MessageListener
  * Vaadin - #1 Java Web UI Framework for Business Applications
    - Vaadin UI, Vaadin Navigation, Vaadin @Push (WebSocket)
    - A single page web application, and purely in Java
  * Spring Boot Application
    - Spring REST
    - Spring Test
    - Spring Security
    - Spring Data JPA
    - Spring AMQP
    - Spring Web
  * H2 In-Memory Database
  * Jayway JsonPath

## The Complete Maven pom.xml
You can find the complete Maven [pom.xml](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/pom.xml).

## Pipeline Processing Design
![demo app](https://cloud.githubusercontent.com/assets/4248714/24716926/97461a2a-19fe-11e7-8461-75e7281175e5.jpg)

## Prerequisites
This Demo App uses RabbitMQ as its Message Broker. You install RabbitMQ locally.

### Install RabbitMQ Locally
Please follow the instructions to download and install RabbitMQ locally before you can run this Demo App.

[Downloading and Installing  RabbitMQ](https://www.rabbitmq.com/download.html)

### Set RabbitMQ host, username, & password in application.properties
Set these values for RabbitMQ in 'application.properties' file only if they're different from the default values.

```
rabbitmq.host=localhost
rabbitmq.username=guest
rabbitmq.password=guest
```

## Install

### Get the Demo App Source Code
You can get the Demo App source code [here](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo.git) using the upper-right "Clone or download" button. 

OR, use the command:

> $ git clone https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo.git

Preferrable, you import the source code into your favorite IDE, such as Eclipse, or IntelliJ IDEA

## Run
You can run this Demo App:

> mvn spring-boot:run

### Demo App in Browser

http://localhost:8080

#### Basic Authentication Login
> username: admin

> password: admin

There are currently two Roles: 'ADMIN', 'USER'

> USER longin: user / user

- 'USER' does not have privilege to call RESTful APIs, or view H2 Database console. See below for detail.
- 'ADMIN' role can perform all tasks.

### Demo App UI

#### Demo View

![demo view](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/demo-view.jpg)

#### Streaming View

![stream view](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/stream-view.jpg)

### Console Log

![stream log](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/stream-log.jpg)

### RESTful APIs

When you login as 'ADMIN' using browser:

> http://localhost:8080/events

Returns all events.

![events](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/events-json.jpg)

> http://localhost:8080/events?type=INFO

Returns 'INFO' type events.

Event types: 'INFO', 'WARNING', 'ERROR'.

> http://localhost:8080/user

Returns current login.user

### RabbitMQ Management UI

#### Exchange: x.topic.logs

![exchange](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/x.topic.logs.jpg)

#### Queues: q.logs.info, q.logs.warning, q.logs.error

![queues](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/q.logs.jpg)

### H2 Database Console

When you login as 'ADMIN' using browser:

> http://localhost:8080/console

Please note the following H2 configurations (also in application.properties)

```
spring.datasource.url=jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver
```

![h2 console](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/blob/master/docs/h2-console.jpg)

To see all persisted events.
