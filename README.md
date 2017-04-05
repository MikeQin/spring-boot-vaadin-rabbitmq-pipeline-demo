# Spring-Boot, Vaadin, RabbitMQ, Data Pipeline Demo

## This Demo App Showcases the following Latest Technology Stack:

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

> rabbitmq.host=localhost

> rabbitmq.username=guest

> rabbitmq.password=guest

### Get the Demo App Source Code
You can get the Demo App source code [here](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo.git) using the upper-right "Clone or download" button. 

OR, use the command:

> $ git clone https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo.git

Preferrable, you import the source code into your favorite IDE, such as Eclipse, or IntelliJ IDEA

## Run
You can run this Demo App:

> spring-boot:run

### Basic Authentication Login
> username: admin

> password: admin

There are currently two Roles: 'ADMIN', 'USER'

> USER longin: user / user

- 'USER' does not have privilege to call RESTful APIs, or view H2 Database console. See below for detail.
- 'ADMIN' role can perform all tasks.

### RESTful APIs

When you login as 'ADMIN':

> /events

Returns all events.

> /events?type=INFO

Returns 'INFO' type events.

Event types: 'INFO', 'WARNING', 'ERROR'.

> /user

Returns current login.user

### H2 Console

When you login as 'ADMIN':

> /console

To see all persisted events.
