package app;

import static app.Constants.Q_LOGS_ERROR;
import static app.Constants.Q_LOGS_INFO;
import static app.Constants.Q_LOGS_WARNING;
import static app.Constants.X_TOPIC_LOGS;

import org.h2.server.web.WebServlet;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.vaadin.spring.annotation.EnableVaadin;

/**
 * @SpringBootApplication includes the following annotations:
 * 
 * @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration
 * 
 */
@Configuration
@ComponentScan({"app","service"})
@EnableAutoConfiguration
@EnableVaadin
public class AppConfig {
	@Bean
	Queue infoQueue() {
		return new Queue(Q_LOGS_INFO, false);
	}

	@Bean
	Queue warningQueue() {
		return new Queue(Q_LOGS_WARNING, false);
	}

	@Bean
	Queue errorQueue() {
		return new Queue(Q_LOGS_ERROR, false);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(X_TOPIC_LOGS);
	}

	@Bean
	Binding bindingInfoQueue() {
		return BindingBuilder.bind(infoQueue()).to(topicExchange()).with(Q_LOGS_INFO);
	}

	@Bean
	Binding bindingWarningQueue() {
		return BindingBuilder.bind(warningQueue()).to(topicExchange()).with(Q_LOGS_WARNING);
	}

	@Bean
	Binding bindingErrorQueue() {
		return BindingBuilder.bind(errorQueue()).to(topicExchange()).with(Q_LOGS_ERROR);
	}

	@Bean
	SimpleMessageListenerContainer container() {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueueNames(Q_LOGS_INFO, Q_LOGS_WARNING, Q_LOGS_ERROR);
		container.setMessageListener(receiver());

		return container;
	}
	
	@Value("${rabbitmq.host}") String host;
	@Value("${rabbitmq.username}") String username;
	@Value("${rabbitmq.password}") String password;

	@Bean
	ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		
		System.out.println("[***] RabbitMQ Host: " + connectionFactory.getHost());
		
		return connectionFactory;
	}

	/**
	 * H2 Database Console
	 * 
	 * @return
	 */
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	/**
	 * MessageListener
	 * 
	 * @return Receiver
	 */
	@Bean
	Receiver receiver() {
		return new Receiver();
	}

	@Bean
	DataGrid dataGrid() {
		return new DataGrid();
	}

	@Bean
	StreamDataGrid streamDataGrid() {
		return new StreamDataGrid();
	}
}
