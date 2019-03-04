package com.agenda.electronic.views.event;

import com.agenda.electronic.controller.ControllerEvent;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.enums.EnumLabel;
import com.agenda.electronic.enums.EnumMessages;
import com.agenda.electronic.util.ValidationsString;
import com.agenda.electronic.views.ViewMenu;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.security.Provider;
import java.time.LocalDate;
import java.util.List;

@UIScope
@SpringView(name = ViewRegisterEvent.VIEW_NAME)
public class ViewRegisterEvent extends VerticalLayout implements View {

    public static final String VIEW_NAME = "RegisterEvent";
    //fields
    private TextField txtTema = new TextField(EnumLabel.TEMA_LABEL.getLabel());
    private TextField txtDuracion = new TextField(EnumLabel.DURACION_LABEL.getLabel());
    private TextField txtUbicacion = new TextField(EnumLabel.UBICACION_LABEL.getLabel());
    private DateField dateFieldFechaInicio = new DateField(EnumLabel.INIT_DATE_LABEL.getLabel());
    private DateField dateFieldFechaFin = new DateField(EnumLabel.FINISH_DATE_LABEL.getLabel());
    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());
    private MenuBar menuBar;
    //Layouts
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Mantenimiento de Evento");
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private Grid<EventoEntity> grid = new Grid<>();
    private ListDataProvider<EventoEntity> dataProvider;
    List<EventoEntity> collectionEvent;

    private String action="";


    @Autowired
    ControllerEvent controllerEvent;
    private EventoEntity eventEntitySelect;

    public ViewRegisterEvent() {
    }

    @PostConstruct
    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        setPropertiesField();
        setLeftPanel();
        createGrid();
        leftLayout.addComponent(grid);
        setRightPanel();

        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        showFields(false);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }

    private void createGrid() {
        List<EventoEntity> collectionEvent = controllerEvent.findAllEvent();
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
                txtTema.setValue(eventEntitySelect.getTema());
                txtDuracion.setValue(eventEntitySelect.getDuracion());
                txtUbicacion.setValue(eventEntitySelect.getLocacion());
                dateFieldFechaInicio.setValue(eventEntitySelect.getFechainicio());
                dateFieldFechaFin.setValue(eventEntitySelect.getFechafin());

            }
        });
    }


    private void setPropertiesField() {

        txtTema.setRequiredIndicatorVisible(true);
        txtDuracion.setRequiredIndicatorVisible(true);
        txtUbicacion.setRequiredIndicatorVisible(true);
        dateFieldFechaInicio.setRequiredIndicatorVisible(true);
        dateFieldFechaFin.setRequiredIndicatorVisible(true);
    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftLayout.setSizeFull();
        principalLayout.addComponent(leftLayout);
    }


    private void setRightPanel() {

        rightLayout.setSizeFull();
        buildButtons();
        buildFields();
        buildButtonsFooter();
        principalLayout.addComponent(rightLayout);

    }

    private void buildButtonsFooter() {
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                clearAction();
                showFields(false);
            }
        });
        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    addEvent();
                } else if (action.equalsIgnoreCase("edit")) {
                    updateEventFields();
                    editEvent(eventEntitySelect);
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteUser();
                    delete(eventEntitySelect);

                }
                clearFields();
                clearAction();
                showFields(false);
                refreshInformationGrid();
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void updateEventFields() {
        eventEntitySelect.setLocacion(txtUbicacion.getValue());
        eventEntitySelect.setDuracion(txtDuracion.getValue());
        eventEntitySelect.setTema(txtTema.getValue());
        eventEntitySelect.setFechainicio(dateFieldFechaInicio.getValue());
        eventEntitySelect.setFechafin(dateFieldFechaFin.getValue());
    }

    private void delete(EventoEntity eventEntitySelect) {
        controllerEvent.delete(eventEntitySelect);
    }

    private void editEvent(EventoEntity eventEntitySelect) {
        controllerEvent.update(eventEntitySelect);
    }

    private void buildFields() {
        fieldsLayout.addComponents(txtTema, txtDuracion, txtUbicacion, dateFieldFechaInicio, dateFieldFechaFin);
        rightLayout.addComponent(fieldsLayout);
    }

    private void buildButtons() {

        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                action = "new";
                clearFields();
                showFields(true);
            }
        });
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    action = "edit";
                    showFields(true);
                }
            }
        });
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    action = "delete";
                    showFields(true);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void addEvent() {

        EventoEntity entityEvent = new EventoEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            try {
                entityEvent.setTema(txtTema.getValue());
                entityEvent.setDuracion(txtDuracion.getValue());
                entityEvent.setLocacion(txtUbicacion.getValue());
                entityEvent.setFechainicio(dateFieldFechaInicio.getValue());
                entityEvent.setFechafin(dateFieldFechaFin.getValue());
                controllerEvent.save(entityEvent);

            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        txtUbicacion.clear();
        dateFieldFechaFin.clear();
        dateFieldFechaInicio.clear();
        txtDuracion.clear();
        txtTema.clear();
    }
    private void clearAction() {
        action = "";
    }
    private void enableFields(boolean value) {
        txtUbicacion.setEnabled(value);
        dateFieldFechaFin.setEnabled(value);
        dateFieldFechaInicio.setEnabled(value);
        txtDuracion.setEnabled(value);
        txtTema.setEnabled(value);

    }

    private void processDeleteUser() {

        try {
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            clearFields();
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(txtTema)) {
            Notification.show("Debe llenar el campo tema", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtDuracion)) {
            Notification.show("Debe llenar el campo duracion", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtUbicacion)) {
            Notification.show("Debe llenar el campo ubicacion", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (dateFieldFechaInicio.toString().isEmpty()) {
            Notification.show("Debe llenar el campo fecha inicio", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (dateFieldFechaFin.toString().isEmpty()) {
            Notification.show("Debe llenar el campo  fecha fin", Notification.Type.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean isValidationFieldEmpty(TextField textField) {
        boolean validation = false;
        if (textField.getValue().isEmpty()) {
            validation = true;
        }
        return validation;
    }

    private void hideFields() {
        visibleGridLayout(false);
    }


    private void visibleGridLayout(boolean visible) {
        fieldsLayout.setVisible(visible);

    }

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private void refreshInformationGrid() {
        collectionEvent = controllerEvent.findAllEvent();
        dataProvider = DataProvider.ofCollection(collectionEvent);
        grid.setDataProvider(dataProvider);

    }
}
