package com.agenda.electronic.views.participation;

import com.agenda.electronic.controller.ControllerParticipation;
import com.agenda.electronic.entity.EventoEntity;
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
@SpringView(name = ViewSelectParticipation.VIEW_NAME)
public class ViewSelectParticipation extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ViewSelectParticipation";

    EventoEntity eventoEntitySelected;

    @Autowired
    ControllerParticipation controllerParticipation;

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
    private Panel principalPanel = new Panel("Seleccionar Participantes");
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<ParticipantesEntity> dataProvider;
    private ListDataProvider<ParticipantesEntity> dataProviderAdded;
    private Grid<ParticipantesEntity> gridParticipantes = new Grid<>();
    private Grid<ParticipantesEntity> gridAddedParticipantes = new Grid<>();
    List<ParticipantesEntity> collectionParticipantes;
    List<ParticipantesEntity> collectionParticipantesAdded;
    private ParticipantesEntity participanteEntitySelected;
    private ParticipantesEntity participanteEntitySelectedAdded;
    private String action;

    public ViewSelectParticipation() {

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
        builbuttons();
        principalLayout.addComponents(leftLayout, rightLayout);
        maxlayout.addComponents(principalLayout, buttonsPrincipalLayout);
        principalPanel.setSizeFull();
        principalPanel.setContent(maxlayout);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }

    private void builbuttons() {
        buttonsPrincipalLayout.addComponents(btnAdd, btnQuitar);
        buttonsPrincipalLayout.setSizeFull();
        buttonsPrincipalLayout.setComponentAlignment(btnAdd, Alignment.MIDDLE_CENTER);
        buttonsPrincipalLayout.setComponentAlignment(btnQuitar, Alignment.MIDDLE_CENTER);
        btnAdd.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (participanteEntitySelected != null) {
                    controllerParticipation.saveRelation(participanteEntitySelected, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
        btnQuitar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (participanteEntitySelectedAdded != null) {
                    controllerParticipation.deleteRelation(participanteEntitySelectedAdded, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
    }

    private void buildLeftLayout() {
        gridParticipantes.setSizeFull();
        leftLayout.addComponent(gridParticipantes);
    }

    private void buildRightLayout() {
        gridAddedParticipantes.setSizeFull();
        rightLayout.addComponent(gridAddedParticipantes);
    }

    private void createLeftGrid() {
        collectionParticipantes = controllerParticipation.findAllParticipations();
        dataProvider = DataProvider.ofCollection(collectionParticipantes);

        gridParticipantes.setEnabled(true);
        gridParticipantes.addColumn(ParticipantesEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        gridParticipantes.addColumn(ParticipantesEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        gridParticipantes.addColumn(ParticipantesEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        gridParticipantes.setDataProvider(dataProvider);
        gridParticipantes.addItemClickListener(new ItemClickListener<ParticipantesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<ParticipantesEntity> event) {
                participanteEntitySelected = event.getItem();
            }
        });
    }

    private void createRightGrid() {
        collectionParticipantesAdded = controllerParticipation.findAllParticipanteAdded(eventoEntitySelected);
        if (collectionParticipantesAdded != null) {
            dataProviderAdded = DataProvider.ofCollection(collectionParticipantesAdded);

            gridAddedParticipantes.setEnabled(true);
            gridAddedParticipantes.addColumn(ParticipantesEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
            gridAddedParticipantes.addColumn(ParticipantesEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
            gridAddedParticipantes.addColumn(ParticipantesEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
            gridAddedParticipantes.setDataProvider(dataProviderAdded);
            gridAddedParticipantes.addItemClickListener(new ItemClickListener<ParticipantesEntity>() {
                @Override
                public void itemClick(Grid.ItemClick<ParticipantesEntity> event) {
                    participanteEntitySelectedAdded = event.getItem();
                }
            });
        }

    }

    private void refreshInformationGridAdded() {
        collectionParticipantesAdded = controllerParticipation.findAllParticipanteAdded(eventoEntitySelected);
        dataProvider = DataProvider.ofCollection(collectionParticipantesAdded);
        gridAddedParticipantes.setDataProvider(dataProvider);
    }

    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute("Event") != null) {
            eventoEntitySelected = (EventoEntity) this.getUI().getSession().getAttribute("Event");
            buildForm();
        }
    }
}
