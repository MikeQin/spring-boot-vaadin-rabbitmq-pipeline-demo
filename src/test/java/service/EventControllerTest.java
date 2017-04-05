package service;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import app.App;
import app.AppConfig;
import app.Constants;
import app.Event;
import app.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class, App.class})
@WebAppConfiguration
public class EventControllerTest {
	
	private MockMvc mockMvc;
	private List<Event> expected = new ArrayList<>();
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
    @Autowired
    WebApplicationContext webApplicationContext;

	@Autowired
	EventRepository repository;
	
    @Before
    public void setup() throws Exception {
        
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        repository.deleteAllInBatch();
        
        expected.add(repository.save(new Event(Constants.INFO)));
        expected.add(repository.save(new Event(Constants.ERROR)));
        expected.add(repository.save(new Event(Constants.WARNING)));
        repository.flush();
    }

	@Test
	public void events() throws Exception {
		mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].type", is(expected.get(0).getType())))
                .andExpect(jsonPath("$[1].type", is(expected.get(1).getType())))
                .andExpect(jsonPath("$[2].type", is(expected.get(2).getType())));
	}
	
	@Test
	public void eventsByType() throws Exception {
		mockMvc.perform(get("/events?type=INFO"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].type", is(expected.get(0).getType())));
	}
}
