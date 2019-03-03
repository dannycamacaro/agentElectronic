package com.agenda.electronic.views.event;

import com.agenda.electronic.enums.EnumLabel;
import com.agenda.electronic.enums.EnumMessages;
import com.agenda.electronic.util.ValidationsString;
import com.agenda.electronic.views.ViewMenu;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@UIScope
@SpringView(name = ViewConsultEvent.VIEW_NAME)
public class ViewConsultEvent extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ConsultEvent";
    //fields
    private TextField txtTema = new TextField(EnumLabel.TEMA_LABEL.getLabel());
    private PasswordField txtDuracion = new PasswordField(EnumLabel.DURACION_LABEL.getLabel());
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
    private Panel principalPanel = new Panel("Consulta de Evento");
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);

    private String action;

    private Collection<String> nameRoles = new ArrayList<>();

    public ViewConsultEvent() {
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
        setRightPanel();

        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
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
            }
        });
        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                } else if (action.equalsIgnoreCase("edit")) {
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteUser();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void buildFields() {
        fieldsLayout.addComponents(txtTema, txtDuracion, txtUbicacion, dateFieldFechaInicio, dateFieldFechaFin);
        rightLayout.addComponent(fieldsLayout);
    }

    private void buildButtons() {

        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                action = "new";
            }
        });
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    action = "edit";
                }
            }
        });
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    action = "delete";
                    enableFields(false);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void clearFields() {

        action = "";
        txtUbicacion.clear();
        dateFieldFechaFin.clear();
        dateFieldFechaInicio.clear();
        txtDuracion.clear();
        txtTema.clear();
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
            Notification.show("Debe llenar el campo Usuario", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtDuracion)) {
            Notification.show("Debe llenar el campo Contraseña", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtUbicacion)) {
            Notification.show("Debe llenar el campo Nombre", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (dateFieldFechaInicio.toString().isEmpty()) {
            Notification.show("Debe llenar el campo Apellido", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (dateFieldFechaFin.toString().isEmpty()) {
            Notification.show("Debe llenar el campo Documento de Identidad", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyString(txtUbicacion.getValue())) {
            Notification.show("Nombre solo puede ser letras", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyString(dateFieldFechaInicio.getValue().toString())) {
            Notification.show("Apellido solo puede ser letras", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyNumbers(dateFieldFechaFin.getValue().toString())) {
            Notification.show("Documento de Identidad solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
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

    private void emptySetValue() {
        action = "";
        txtTema.clear();
        txtDuracion.clear();
        txtUbicacion.clear();
        dateFieldFechaInicio.clear();
        dateFieldFechaFin.clear();
    }


}
