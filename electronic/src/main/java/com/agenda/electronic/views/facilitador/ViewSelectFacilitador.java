package com.agenda.electronic.views.facilitador;

import com.agenda.electronic.controller.ControllerFacilitador;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.FacilitadoresEntity;
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
@SpringView(name = ViewSelectFacilitador.VIEW_NAME)
public class ViewSelectFacilitador extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ViewSelectFacilitador";
    EventoEntity eventoEntitySelected;

    @Autowired
    ControllerFacilitador controllerFacilitador;

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
    private Panel principalPanel = new Panel("Seleccionar Facilitadores");
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<FacilitadoresEntity> dataProvider;
    private ListDataProvider<FacilitadoresEntity> dataProviderAdded;
    private Grid<FacilitadoresEntity> gridFacilitadores = new Grid<>();
    private Grid<FacilitadoresEntity> gridAddedFacilitadores = new Grid<>();
    List<FacilitadoresEntity> collectionFacilitadores;
    List<FacilitadoresEntity> collectionFacilitadoresAdded;
    private FacilitadoresEntity facilitadoresEntitySelected;
    private FacilitadoresEntity facilitadoresEntitySelectedAdded;
    private String action;

    public ViewSelectFacilitador() {

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
                if (facilitadoresEntitySelected != null) {
                    //collectionFacilitadoresAdded.add(facilitadoresEntitySelected);
                    controllerFacilitador.saveRelation(facilitadoresEntitySelected, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
        btnQuitar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (facilitadoresEntitySelectedAdded != null) {
                    //collectionFacilitadoresAdded.remove(facilitadoresEntitySelectedAdded);
                    controllerFacilitador.deleteRelation(facilitadoresEntitySelectedAdded, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
    }

    private void buildLeftLayout() {
        gridFacilitadores.setSizeFull();
        leftLayout.addComponent(gridFacilitadores);
    }

    private void buildRightLayout() {
        gridAddedFacilitadores.setSizeFull();
        rightLayout.addComponent(gridAddedFacilitadores);
    }

    private void createLeftGrid() {
        collectionFacilitadores = controllerFacilitador.findAllFacilitador();
        dataProvider = DataProvider.ofCollection(collectionFacilitadores);

        gridFacilitadores.setEnabled(true);
        gridFacilitadores.addColumn(FacilitadoresEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        gridFacilitadores.addColumn(FacilitadoresEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        gridFacilitadores.addColumn(FacilitadoresEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        gridFacilitadores.setDataProvider(dataProvider);
        gridFacilitadores.addItemClickListener(new ItemClickListener<FacilitadoresEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<FacilitadoresEntity> event) {
                facilitadoresEntitySelected = event.getItem();
            }
        });
    }

    private void createRightGrid() {
        collectionFacilitadoresAdded = controllerFacilitador.findAllFacilitadorAdded(eventoEntitySelected);
        if (collectionFacilitadoresAdded != null) {
            dataProviderAdded = DataProvider.ofCollection(collectionFacilitadoresAdded);

            gridAddedFacilitadores.setEnabled(true);
            gridAddedFacilitadores.addColumn(FacilitadoresEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
            gridAddedFacilitadores.addColumn(FacilitadoresEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
            gridAddedFacilitadores.addColumn(FacilitadoresEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
            gridAddedFacilitadores.setDataProvider(dataProviderAdded);
            gridAddedFacilitadores.addItemClickListener(new ItemClickListener<FacilitadoresEntity>() {
                @Override
                public void itemClick(Grid.ItemClick<FacilitadoresEntity> event) {
                    facilitadoresEntitySelectedAdded = event.getItem();
                }
            });
        }

    }

    private void refreshInformationGridAdded() {
        collectionFacilitadoresAdded = controllerFacilitador.findAllFacilitadorAdded(eventoEntitySelected);
        dataProvider = DataProvider.ofCollection(collectionFacilitadoresAdded);
        gridAddedFacilitadores.setDataProvider(dataProvider);

    }

    @Override
    public void attach() {

        if (this.getUI() != null && this.getUI().getSession().getAttribute("Event") != null) {
            eventoEntitySelected = (EventoEntity) this.getUI().getSession().getAttribute("Event");
            buildForm();
        }
    }
}
