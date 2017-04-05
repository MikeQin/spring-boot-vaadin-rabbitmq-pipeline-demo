package app;

import static app.Constants.ERROR;
import static app.Constants.INFO;
import static app.Constants.WARNING;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.spring.annotation.UIScope;

@UIScope
@Service
public class Receiver implements MessageListener {
    
    @Autowired
    EventRepository repository;
    
    public List<Event> getInfoEvents() {
    	return repository.findByType(INFO);
    }
    
    public List<Event> getWarningEvents() {
    	return repository.findByType(WARNING);
    }
    
    public List<Event> getErrorEvents() {
    	return repository.findByType(ERROR);
    }    

	@Override
	public void onMessage(Message message) {
		
		MessageProperties msgProps = message.getMessageProperties();
		String routingKey = msgProps.getReceivedRoutingKey();
		String msgBody = new String(message.getBody(), StandardCharsets.UTF_8);
		
        System.out.println("[x] Received <" + msgBody + ">, routingKey=" + routingKey);
        
        Event event = Event.valueOf(msgBody);
        event.setRoutingKey(routingKey);
        // Persist Event
        repository.saveAndFlush(event);
	}

}
