package com.agenda.electronic.views.event;

import com.agenda.electronic.controller.ControllerEvent;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.enums.EnumLabel;
import com.agenda.electronic.navigator.UniverseNavigator;
import com.agenda.electronic.views.ViewMenu;
import com.agenda.electronic.views.facilitador.ViewSelectFacilitador;
import com.agenda.electronic.views.institucion.ViewInstitucionesSelect;
import com.agenda.electronic.views.participation.ViewSelectParticipation;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultEvent.VIEW_NAME)
public class ViewConsultEvent extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ConsultEvent";

    private MenuBar menuBar;
    //Layouts
    private VerticalLayout leftLayout = new VerticalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Seleccione un Evento");
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private Grid<EventoEntity> grid = new Grid<>();
    private ListDataProvider<EventoEntity> dataProvider;
    private EventoEntity eventEntitySelect;
    private List<EventoEntity> collectionEvent;
    String filterUrl;



    @Autowired
    ControllerEvent controllerEvent;

    public ViewConsultEvent() {
    }

    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        setLeftPanel();
        createGrid();
        grid.setSizeFull();
        leftLayout.addComponent(grid);
        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }

    private void createGrid() {
        collectionEvent = controllerEvent.findAllEvent();
        dataProvider = DataProvider.ofCollection(collectionEvent);

        grid.setEnabled(true);
        grid.addColumn(EventoEntity::getTema).setCaption(EnumLabel.TEMA_LABEL.getLabel());
        grid.addColumn(EventoEntity::getLocacion).setCaption(EnumLabel.UBICACION_LABEL.getLabel());
        grid.addColumn(EventoEntity::getFechainicio).setCaption(EnumLabel.INIT_DATE_LABEL.getLabel());
        grid.addColumn(EventoEntity::getFechafin).setCaption(EnumLabel.FINISH_DATE_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<EventoEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<EventoEntity> event) {
                eventEntitySelect = event.getItem();
                if (event.getItem()!= null){
                    grid.getUI().getSession().setAttribute("Event", event.getItem());
                }
                if (filterUrl.equalsIgnoreCase("participation")){
                    UniverseNavigator.navigate(ViewSelectParticipation.VIEW_NAME);
                }else  if (filterUrl.equalsIgnoreCase("facilitador")){
                    UniverseNavigator.navigate(ViewSelectFacilitador.VIEW_NAME);
                }else  if (filterUrl.equalsIgnoreCase("institucion")){
                    UniverseNavigator.navigate(ViewInstitucionesSelect.VIEW_NAME);
                }else  if (filterUrl.equalsIgnoreCase("ConsultInformation")){
                    UniverseNavigator.navigate(ViewConsultInformationEvent.VIEW_NAME);
                }
            }
        });
    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftLayout.setSizeFull();
        principalLayout.addComponent(leftLayout);
    }

    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute("filterUrl") != null) {
            filterUrl = this.getUI().getSession().getAttribute("filterUrl").toString();
            buildForm();
        }
    }
}