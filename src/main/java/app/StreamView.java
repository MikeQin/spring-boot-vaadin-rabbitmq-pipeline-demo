package app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView(name = Constants.VIEW_STREAM)
public class StreamView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private Grid<app.Event> grid;

	@Autowired
	StreamDataGrid streamDataGrid;

	@Autowired
	Sender sender;

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to Stream View!");
	}

	@PostConstruct
	void init() {
		setupLayout();
		addHeaderAndButtons();
		addDataGrid();
	}

	private void setupLayout() {
		// layout = new VerticalLayout();
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		// setContent(layout);
	}

	private void addHeaderAndButtons() {
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("80%");

		Label header = new Label("Streaming Console");
		header.addStyleName(ValoTheme.LABEL_H2);

		Button showButton = new Button("");
		showButton.setCaption("Stream");
		showButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		// showButton.setIcon(VaadinIcons.PLUS_CIRCLE);
		showButton.addClickListener(click -> {
			System.out.println("[x] Show button clicked ...");
			// Start the data feed thread
			new FeederThread().start();	
		});

		Button sendButton = new Button("");
		sendButton.setCaption("Send");
		sendButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		sendButton.setIcon(VaadinIcons.PLUS);
		sendButton.addClickListener(click -> {
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					System.out.println("[x] Send button clicked ...");
					sender.stream();
				}
			};

			new Thread(r).start();
		});

		topLayout.addComponent(header);
		topLayout.setComponentAlignment(header, Alignment.BOTTOM_CENTER);
		topLayout.addComponent(showButton);
		topLayout.setComponentAlignment(showButton, Alignment.MIDDLE_CENTER);
		topLayout.addComponent(sendButton);
		topLayout.setComponentAlignment(sendButton, Alignment.MIDDLE_CENTER);

		addComponent(topLayout);
	}

	private void addDataGrid() {

		grid = streamDataGrid.get();
		grid.setWidth("100%");
		
		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		bottomLayout.addComponent(grid);
		bottomLayout.setWidth("100%");

		addComponent(bottomLayout);
	}

	class FeederThread extends Thread {
		int count = 0;

		@Override
		public void run() {
			try {
				// Update the data for a while
				while (count < 10) {
					Thread.sleep(1000);

					// Runnable
					getUI().access(() -> {
						streamDataGrid.setItems();
						System.out.println("[x] pushing ...");
					});
					count++;
				}

				// Inform that we have stopped running
				// Runnable
				getUI().access(() -> {
					Notification.show("Done!");
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}