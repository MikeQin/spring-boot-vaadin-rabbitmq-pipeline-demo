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
@SpringView(name = Constants.VIEW_DEFAULT)
public class DemoView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	@Autowired
	DataGrid dataGrid;

	@Autowired
	Sender sender;

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to Demo View!");
	}

	@PostConstruct
	void init() {
		setupLayout();
		addHeaderAndButtons();
		addDataGrid();
	}

	private void setupLayout() {
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	}

	private void addHeaderAndButtons() {
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("80%");

		Label header = new Label("Message Console");
		header.addStyleName(ValoTheme.LABEL_H2);

		Button showButton = new Button("");
		showButton.setCaption("Show");
		showButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		// showButton.setIcon(VaadinIcons.PLUS_CIRCLE);
		showButton.addClickListener(click -> {
			System.out.println("[x] Show button clicked ...");
			dataGrid.setItems();
		});

		Button sendButton = new Button("");
		sendButton.setCaption("Send");
		sendButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		sendButton.setIcon(VaadinIcons.PLUS);

		sendButton.addClickListener(click -> {
			System.out.println("[x] Send button clicked ...");

			Runnable runner = new Runnable() {
				@Override
				public void run() {
					sender.send();
				}
			};

			new Thread(runner).start();
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

		Grid<app.Event> grid = dataGrid.get();
		grid.setWidth("100%");

		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		bottomLayout.addComponent(grid);
		bottomLayout.setWidth("100%");

		addComponent(bottomLayout);
	}
}
