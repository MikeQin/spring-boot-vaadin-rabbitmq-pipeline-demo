package app;

import static app.Constants.INFO;
import static app.Constants.Q_LOGS_INFO;
import static app.Constants.Q_LOGS_WARNING;
import static app.Constants.WARNING;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.spring.annotation.UIScope;

@UIScope
@Service
public class Sender {

	@Autowired
    RabbitTemplate rabbitTemplate;

    public void send() {
        System.out.println("Sending message ...");
        
        for (int i=0; i<10; i++) {
        	rabbitTemplate.convertAndSend(Q_LOGS_INFO, new Event(INFO).toString());
        	
        	sleep(1000);
        }
    }
    
    public void stream() {
        System.out.println("Streaming data ...");
        
        for (int i=0; i<100; i++) {
        	rabbitTemplate.convertAndSend(Q_LOGS_WARNING, new Event(WARNING).toString());
        	
        	sleep(1000);
        }
    }    

	private void sleep(int ms) {
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
