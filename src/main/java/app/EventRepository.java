package app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
	
	List<Event> findByType(String type);
	List<Event> findByRoutingKey(String routingKey);

}
