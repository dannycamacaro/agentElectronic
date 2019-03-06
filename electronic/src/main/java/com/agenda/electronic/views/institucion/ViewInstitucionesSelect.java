package com.agenda.electronic.views.institucion;

import com.agenda.electronic.controller.ControllerInstituciones;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.InstitucionesEntity;
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
@SpringView(name = ViewInstitucionesSelect.VIEW_NAME)
public class ViewInstitucionesSelect extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ViewInstitucionesSelect";
    EventoEntity eventoEntitySelected;

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
    private Panel principalPanel = new Panel("Seleccionar Institucion");
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<InstitucionesEntity> dataProvider;
    private ListDataProvider<InstitucionesEntity> dataProviderAdded;
    private Grid<InstitucionesEntity> gridInstituciones = new Grid<>();
    private Grid<InstitucionesEntity> gridAddedInstituciones = new Grid<>();
    List<InstitucionesEntity> collectionInstituciones;
    List<InstitucionesEntity> collectionInstitucionesAdded;
    private InstitucionesEntity institucionesEntitySelected;
    private InstitucionesEntity institucionesEntitySelectedAdded;
    private String action;

    public ViewInstitucionesSelect() {

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
                if (institucionesEntitySelected != null) {
                    controllerInstituciones.saveRelation(institucionesEntitySelected, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
        btnQuitar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (institucionesEntitySelectedAdded != null) {
                    controllerInstituciones.deleteRelation(institucionesEntitySelectedAdded, eventoEntitySelected);
                    refreshInformationGridAdded();
                }
            }
        });
    }

    private void buildLeftLayout() {
        gridInstituciones.setSizeFull();
        leftLayout.addComponent(gridInstituciones);
    }

    private void buildRightLayout() {
        gridAddedInstituciones.setSizeFull();
        rightLayout.addComponent(gridAddedInstituciones);
    }

    private void createLeftGrid() {
        collectionInstituciones = controllerInstituciones.findAllParticipations();
        dataProvider = DataProvider.ofCollection(collectionInstituciones);

        gridInstituciones.setEnabled(true);
        gridInstituciones.addColumn(InstitucionesEntity::getNombeinstitucion).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        gridInstituciones.addColumn(InstitucionesEntity::getPersonacontacto).setCaption(EnumLabel.PERSONA_CONTACTO_LABEL.getLabel());
        gridInstituciones.addColumn(InstitucionesEntity::getTelefono).setCaption(EnumLabel.NUMERO_TELEFONO_LABEL.getLabel());
        gridInstituciones.setDataProvider(dataProvider);
        gridInstituciones.addItemClickListener(new ItemClickListener<InstitucionesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<InstitucionesEntity> event) {
                institucionesEntitySelected = event.getItem();
            }
        });
    }

    private void createRightGrid() {
        collectionInstitucionesAdded = controllerInstituciones.findAllParticipanteAdded(eventoEntitySelected);
        if (collectionInstitucionesAdded != null) {
            dataProviderAdded = DataProvider.ofCollection(collectionInstitucionesAdded);

            gridAddedInstituciones.setEnabled(true);
            gridAddedInstituciones.addColumn(InstitucionesEntity::getNombeinstitucion).setCaption(EnumLabel.NAMES_LABEL.getLabel());
            gridAddedInstituciones.addColumn(InstitucionesEntity::getPersonacontacto).setCaption(EnumLabel.PERSONA_CONTACTO_LABEL.getLabel());
            gridAddedInstituciones.addColumn(InstitucionesEntity::getTelefono).setCaption(EnumLabel.NUMERO_TELEFONO_LABEL.getLabel());
            gridAddedInstituciones.setDataProvider(dataProviderAdded);
            gridAddedInstituciones.addItemClickListener(new ItemClickListener<InstitucionesEntity>() {
                @Override
                public void itemClick(Grid.ItemClick<InstitucionesEntity> event) {
                    institucionesEntitySelectedAdded = event.getItem();
                }
            });
        }

    }

    private void refreshInformationGridAdded() {
        collectionInstitucionesAdded = controllerInstituciones.findAllParticipanteAdded(eventoEntitySelected);
        dataProvider = DataProvider.ofCollection(collectionInstitucionesAdded);
        gridAddedInstituciones.setDataProvider(dataProvider);
    }

    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute("Event") != null) {
            eventoEntitySelected = (EventoEntity) this.getUI().getSession().getAttribute("Event");
            buildForm();
        }
    }
}


