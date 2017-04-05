package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Event;
import app.EventRepository;

@RestController
public class EventController {

	@Autowired
	EventRepository repository;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> events(@RequestParam(value = "type", defaultValue = "ALL") String type) {
		List<Event> events = null;

		System.out.println("[***] /events?type=" + type.toUpperCase());

		if (type.equals("ALL")) {
			events = repository.findAll();
		} else {
			events = repository.findByType(type.toUpperCase());
		}

		return events;
	}
}
