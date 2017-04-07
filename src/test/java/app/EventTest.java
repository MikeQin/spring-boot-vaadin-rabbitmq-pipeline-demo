package app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.AppConfig;
import app.Event;
import app.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppConfig.class, App.class })
public class EventTest {

	@Autowired
	EventRepository repository;

	@Before
	public void setup() {
		repository.deleteAllInBatch();
		repository.flush();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void save() {

		Event expected = new Event("ERROR");

		repository.save(expected);
		Event actual = repository.findAll().get(0);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void valueOf() {

		Event expected = new Event();
		String eventStr = expected.toString();
		Event actual = Event.valueOf(eventStr);

		assertThat(actual).isEqualTo(expected);

		System.out.println("actual: " + actual);
	}

	@Test
	public void findByType() {
		List<Event> events = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			events.add(new Event("INFO"));
		}

		for (int i = 0; i < 5; i++) {
			events.add(new Event("ERROR"));
		}

		repository.save(events);
		repository.flush();

		List<Event> infos = repository.findByType("INFO");
		List<Event> errors = repository.findByType("ERROR");

		assertThat(10).isEqualTo(infos.size());
		assertThat(5).isEqualTo(errors.size());
	}
}
