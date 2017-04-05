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

	<modelVersion>4.0.0</modelVersion>

	<groupId>spring-boot-vaadin-rabbitmq-pipeline-demo</groupId>
	<artifactId>demo-app</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>demo-app</name>
	<description>RabbitMQ, AMQP, Vaadin, Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.2.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.vaadin</groupId>
			<artifactId>vaadin-spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>com.vaadin</groupId>
			<artifactId>vaadin-push</artifactId>
			<!-- <version>${vaadin.version}</version> -->
		</dependency>
		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-api</artifactId>
			<version>7.0</version>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<!-- <scope>runtime</scope> -->
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.jayway.jsonpath</groupId>
			<artifactId>json-path</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.vaadin</groupId>
				<artifactId>vaadin-bom</artifactId>
				<version>8.0.0</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-releases</id>
			<url>https://repo.spring.io/libs-release</url>
		</repository>
	</repositories>

	<pluginRepositories>
		<pluginRepository>
			<id>spring-releases</id>
			<url>https://repo.spring.io/libs-release</url>
		</pluginRepository>
	</pluginRepositories>

## Pipeline Processing Design
![demo app](https://cloud.githubusercontent.com/assets/4248714/24716926/97461a2a-19fe-11e7-8461-75e7281175e5.jpg)

## Prerequisites
This Demo App uses RabbitMQ as its Message Broker. You install RabbitMQ locally.

### Install RabbitMQ Locally
Please follow the instructions to download and install RabbitMQ locally before you can run this Demo App.

![Downloading and Installing  RabbitMQ](https://www.rabbitmq.com/download.html)

### Set RabbitMQ host, username, & password in application.properties
Set these values for RabbitMQ in 'application.properties' file

> rabbitmq.host=localhost

> rabbitmq.username=guest

> rabbitmq.password=guest

### Get the Demo App Source Code
Get the Demo App source code ![here](https://github.com/MikeQin/spring-boot-vaadin-rabbitmq-pipeline-demo/edit/master). 

Preferrable, you import the source code into your favorite IDE, such as Eclipse, or IntelliJ IDEA

## Run
You can run this Demo App:

> spring-boot:run
