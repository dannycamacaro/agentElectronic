package com.agenda.electronic.views.event;

import com.agenda.electronic.controller.ControllerFacilitador;
import com.agenda.electronic.controller.ControllerInstituciones;
import com.agenda.electronic.controller.ControllerParticipation;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.FacilitadoresEntity;
import com.agenda.electronic.entity.ParticipantesEntity;
import com.agenda.electronic.enums.EnumLabel;
import com.agenda.electronic.views.ViewMenu;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UIScope
@SpringView(name = ViewConsultInformationEvent.VIEW_NAME)
public class ViewConsultInformationEvent extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ViewConsultInformationEvent";

    EventoEntity eventoEntitySelected;

    @Autowired
    ControllerFacilitador controllerFacilitador;    
    @Autowired
    ControllerParticipation controllerParticipation;    
    @Autowired
    ControllerInstituciones controllerInstituciones;   

    //Buttons
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private Button btnAdd = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnQuitar = new Button(EnumLabel.QUITAR_LABEL.getLabel());

    private MenuBar menuBar;
    //Layouts
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private VerticalLayout maxlayout = new VerticalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Consulta de datos de Evento");
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<FacilitadoresEntity> dataProvider;
    private ListDataProvider<ParticipantesEntity> dataProviderPartcipates;
    private Grid<FacilitadoresEntity> gridFacilitadores = new Grid<>();
    private Grid<ParticipantesEntity> gridParticipantes = new Grid<>();
    List<FacilitadoresEntity> collectionFacilitadores;
    List<ParticipantesEntity> collectionParticipantes;

    public ViewConsultInformationEvent() {

    }

    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        createLeftGrid();
        createRightGrid();
        buildLeftLayout();
        buildRightLayout();
        principalLayout.addComponents(leftLayout, rightLayout);
        maxlayout.addComponents(principalLayout, buttonsPrincipalLayout);
        principalPanel.setSizeFull();
        principalPanel.setContent(maxlayout);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }

    private void buildLeftLayout() {
        gridFacilitadores.setSizeFull();
        leftLayout.addComponent(gridFacilitadores);
    }

    private void buildRightLayout() {
        gridParticipantes.setSizeFull();
        rightLayout.addComponent(gridParticipantes);
    }

    private void createLeftGrid() {
        collectionFacilitadores = controllerFacilitador.findAllFacilitadorAdded(eventoEntitySelected);
        dataProvider = DataProvider.ofCollection(collectionFacilitadores);

        gridFacilitadores.setEnabled(true);
        gridFacilitadores.addColumn(FacilitadoresEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        gridFacilitadores.addColumn(FacilitadoresEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        gridFacilitadores.addColumn(FacilitadoresEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        gridFacilitadores.setDataProvider(dataProvider);
    }

    private void createRightGrid() {
        collectionParticipantes = controllerParticipation.findAllParticipanteAdded(eventoEntitySelected);
        if (collectionParticipantes != null) {
            dataProviderPartcipates = DataProvider.ofCollection(collectionParticipantes);

            gridParticipantes.setEnabled(true);
            gridParticipantes.addColumn(ParticipantesEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
            gridParticipantes.addColumn(ParticipantesEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
            gridParticipantes.addColumn(ParticipantesEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
            gridParticipantes.setDataProvider(dataProviderPartcipates);
        }
    }

    private void refreshInformationGridAdded() {
        collectionParticipantes = controllerParticipation.findAllParticipanteAdded(eventoEntitySelected);
        dataProviderPartcipates = DataProvider.ofCollection(collectionParticipantes);
        gridParticipantes.setDataProvider(dataProviderPartcipates);

    }

    @Override
    public void attach() {

        if (this.getUI() != null && this.getUI().getSession().getAttribute("Event") != null) {
            eventoEntitySelected = (EventoEntity) this.getUI().getSession().getAttribute("Event");
            buildForm();
        }
    }
}