package app;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String type;
	private String time;
	private String routingKey;
	@JsonIgnore
	@Transient
	private String[] types = {"INFO", "WARNING", "ERROR"};	
	
	public Event() {
		this(null);
	}
	
	public Event(String type) {
		
		this.id = UUID.randomUUID().toString().toUpperCase();
		this.type = type;
		this.time = sdf.format(new Date());
		
		if (type == null) {
			Random r = new Random();
			this.type = types[r.nextInt(types.length)];
		}
	}

	public String getId() {
		return id;
	}
	
	public String getTime() {
		return this.time;
	}

	public String getType() {
		return type;
	}
	
	void setId(String id) {
		this.id = id;
	}
	
	void setType(String type) {
		this.type = type;
	}
	
	void setTime(String time) {
		this.time = time;
	}
	
	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public static Event valueOf(String msg) {
		Event event = new Event();
		int i = msg.indexOf('=') + 1;
		int end = msg.indexOf(',');
		event.setId(msg.substring(i, end));
		
		i = msg.indexOf('=', i) + 1;
		end = msg.indexOf(',', i);
		event.setType(msg.substring(i, end));
		
		i = msg.indexOf('=', i) + 1;
		end = msg.indexOf(']');
		event.setTime(msg.substring(i, end));
		
		return event;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
