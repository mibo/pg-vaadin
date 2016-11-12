package de.mirb.pg.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.Collection;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    final VerticalLayout layout = new VerticalLayout();

    final TextField name = new TextField();
    name.setCaption("Type your name here:");

    Button button = new Button("Click Me");
    button.addClickListener(e -> {
      layout.addComponent(new Label("Thanks " + name.getValue()
          + ", it works!"));
    });

    layout.addComponents(name, button, createGrid());
    layout.setMargin(true);
    layout.setSpacing(true);

    setContent(layout);
  }

  private Grid createGrid() {
    Grid grid = new Grid();

    IndexedContainer container = getIndexedContainer();

    grid.setContainerDataSource(container);
//    grid.setColumnOrder("Name", "Lastname", "Nickname");
    grid.setSelectionMode(Grid.SelectionMode.NONE);
    grid.setWidth("800px");
    grid.setHeight("500px");
    Grid.HeaderRow filterRow = grid.appendHeaderRow();

    // Set up a filter for all columns
    for (Object pid : grid.getContainerDataSource().getContainerPropertyIds()) {
      Grid.HeaderCell cell = filterRow.getCell(pid);

      // Have an input field to use for filter
      TextField filterField = new TextField();
      filterField.setColumns(8);

      // Update filter When the filter input is changed
      filterField.addTextChangeListener(change -> {
        // Can't modify filters so need to replace
        container.removeContainerFilters(pid);

        // (Re)create the filter if necessary
        if (!change.getText().isEmpty())
          container.addContainerFilter(
              new SimpleStringFilter(pid, change.getText(), true, false));
      });
      cell.setComponent(filterField);
    }

    return grid;
  }

  private IndexedContainer getIndexedContainer() {
    IndexedContainer container = new IndexedContainer();
    container.addContainerProperty("Name", String.class, "");
    container.addContainerProperty("Lastname", String.class, "");
    container.addContainerProperty("Nickname", String.class, "");

//    container.addItem(new Person("Michael", "Bolz", "mibo"));
    Collection<Person> persons = PersonBuilder.randomPersons(20);
    persons.forEach(p -> {
      Item i = container.getItem(container.addItem());
      i.getItemProperty("Name").setValue(p.getName());
      i.getItemProperty("Lastname").setValue(p.getLastname());
      i.getItemProperty("Nickname").setValue(p.getNickname());
    });

    return container;
  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
