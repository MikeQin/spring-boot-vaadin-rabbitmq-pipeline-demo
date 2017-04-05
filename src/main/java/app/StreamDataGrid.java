package app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;

@UIScope
@SpringComponent
public class StreamDataGrid {

	@Autowired
	Receiver receiver;

	private Grid<Event> grid;

	@PostConstruct
	public void build() {

		grid = new Grid<>();
		grid.addColumn(Event::getId).setCaption("ID");
		grid.addColumn(Event::getType).setCaption("Type");
		grid.addColumn(Event::getTime).setCaption("Time");
	}

	public Grid<Event> get() {
		return grid;
	}

	public void setItems() {
		grid.setItems(receiver.getWarningEvents());
	}
}
