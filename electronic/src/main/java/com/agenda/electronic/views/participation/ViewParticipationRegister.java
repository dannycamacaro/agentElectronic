package com.agenda.electronic.views.participation;

import com.agenda.electronic.controller.ControllerParticipation;
import com.agenda.electronic.entity.ParticipantesEntity;
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
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringView(name = ViewParticipationRegister.VIEW_NAME)
public class ViewParticipationRegister extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ViewParticipationRegister";
    @Autowired
    ControllerParticipation controllerParticipation;
    //fields
    private TextField txtIdentityDocument = new TextField(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
    private TextField txtNames = new TextField(EnumLabel.NAMES_LABEL.getLabel());
    private TextField txtLastNames = new TextField(EnumLabel.LAST_NAME_LABEL.getLabel());
    private TextField txtAge = new TextField(EnumLabel.AGE_LABEL.getLabel());
    private TextField txtCurso = new TextField(EnumLabel.CURSO_LABEL.getLabel());
    private TextField txtSeccion = new TextField(EnumLabel.SECCION_LABEL.getLabel());
    private TextField txtPhoneNumber = new TextField(EnumLabel.PHONE_NUMBER_LABEL.getLabel());
    private TextField txtEmail = new TextField(EnumLabel.EMAIL_LABEL.getLabel());
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
    private Panel principalPanel = new Panel("Mantenimiento de Participantes");
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<ParticipantesEntity> dataProvider;
    private Grid<ParticipantesEntity> grid = new Grid<>();
    List<ParticipantesEntity> collectionParticipante;
    private ParticipantesEntity participationSelected;
    private String action;

    private Collection<String> nameRoles = new ArrayList<>();

    public ViewParticipationRegister() {
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
        createGrid();
        leftLayout.addComponent(grid);
        showFields(false);
        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuBar, principalPanel);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }

    private void createGrid() {
        List<ParticipantesEntity> collectionParticipation = controllerParticipation.findAllParticipations();
        dataProvider = DataProvider.ofCollection(collectionParticipation);

        grid.setEnabled(true);
        grid.addColumn(ParticipantesEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        grid.addColumn(ParticipantesEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        grid.addColumn(ParticipantesEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<ParticipantesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<ParticipantesEntity> event) {
                 participationSelected = event.getItem();
                txtIdentityDocument.setValue(participationSelected.getIddocumento());
                txtAge.setValue(participationSelected.getEdad().toString());
                txtCurso.setValue(participationSelected.getCurso());
                txtEmail.setValue(participationSelected.getCorreo());
                txtLastNames.setValue(participationSelected.getApellidos());
                txtNames.setValue(participationSelected.getNombres());
                txtPhoneNumber.setValue(participationSelected.getTelefono());
                txtSeccion.setValue(participationSelected.getSeccion());

            }
        });
    }


    private void setPropertiesField() {

        txtNames.setRequiredIndicatorVisible(true);
        txtLastNames.setRequiredIndicatorVisible(true);
        txtCurso.setRequiredIndicatorVisible(true);
        txtSeccion.setRequiredIndicatorVisible(true);
        txtIdentityDocument.setRequiredIndicatorVisible(true);
        txtAge.setRequiredIndicatorVisible(true);
        txtPhoneNumber.setRequiredIndicatorVisible(true);
        txtEmail.setRequiredIndicatorVisible(true);
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
                    addParticipation();
                } else if (action.equalsIgnoreCase("edit")) {
                    editParticipation();
                } else if (action.equalsIgnoreCase("delete")) {
                    deleteParticipation();
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

    private void deleteParticipation() {
        controllerParticipation.delete(participationSelected);
    }

    private void editParticipation() {
        participationSelected.setIddocumento(txtIdentityDocument.getValue());
        participationSelected.setEdad(Integer.parseInt(txtAge.getValue()));
        participationSelected.setCurso(txtCurso.getValue());
        participationSelected.setCorreo(txtEmail.getValue());
        participationSelected.setApellidos(txtLastNames.getValue());
        participationSelected.setNombres(txtNames.getValue());
        participationSelected.setTelefono(txtPhoneNumber.getValue());
        participationSelected.setSeccion(txtSeccion.getValue());
        controllerParticipation.update(participationSelected);
    }

    private void addParticipation() {
        ParticipantesEntity participantesEntity = new ParticipantesEntity();
        participantesEntity.setIddocumento(txtIdentityDocument.getValue());
        participantesEntity.setEdad(Integer.parseInt(txtAge.getValue()));
        participantesEntity.setCurso(txtCurso.getValue());
        participantesEntity.setCorreo(txtEmail.getValue());
        participantesEntity.setApellidos(txtLastNames.getValue());
        participantesEntity.setNombres(txtNames.getValue());
        participantesEntity.setTelefono(txtPhoneNumber.getValue());
        participantesEntity.setSeccion(txtSeccion.getValue());
        controllerParticipation.save(participantesEntity);
    }

    private void buildFields() {
        txtEmail.setPlaceholder("RamonSuarez@conors.com");
        fieldsLayout.addComponents(txtNames, txtLastNames, txtCurso, txtSeccion, txtIdentityDocument, txtAge, txtPhoneNumber, txtEmail);
        rightLayout.addComponent(fieldsLayout);
    }

    private void buildButtons() {

        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                showFields(true);
                action = "new";
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
                    enableFields(false);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void clearFields() {

        action = "";
        txtAge.clear();
        txtEmail.clear();
        txtCurso.clear();
        txtIdentityDocument.clear();
        txtSeccion.clear();
        txtLastNames.clear();
        txtNames.clear();
        txtPhoneNumber.clear();
    }

    private void enableFields(boolean value) {

        txtAge.setEnabled(value);
        txtEmail.setEnabled(value);
        txtCurso.setEnabled(value);
        txtIdentityDocument.setEnabled(value);
        txtSeccion.setEnabled(value);
        txtLastNames.setEnabled(value);
        txtNames.setEnabled(value);
        txtPhoneNumber.setEnabled(value);

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
        if (isValidationFieldEmpty(txtNames)) {
            Notification.show("Debe llenar el campo Usuario", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtLastNames)) {
            Notification.show("Debe llenar el campo Contraseña", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtCurso)) {
            Notification.show("Debe llenar el campo Nombre", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtSeccion)) {
            Notification.show("Debe llenar el campo Apellido", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtIdentityDocument)) {
            Notification.show("Debe llenar el campo Documento de Identidad", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtAge)) {
            Notification.show("Debe llenar el campo Edad", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtPhoneNumber)) {
            Notification.show("Debe llenar el campo Numero Telefonico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtEmail)) {
            Notification.show("Debe llenar el campo Email", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyString(txtCurso.getValue())) {
            Notification.show("Nombre solo puede ser letras", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyString(txtSeccion.getValue())) {
            Notification.show("Apellido solo puede ser letras", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyNumbers(txtIdentityDocument.getValue())) {
            Notification.show("Documento de Identidad solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyNumbers(txtAge.getValue())) {
            Notification.show("Edad solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyNumbers(txtPhoneNumber.getValue())) {
            Notification.show("Numero Telefonico solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.validEmail(txtEmail.getValue())) {
            Notification.show("El formato de Email es invalido", Notification.Type.ERROR_MESSAGE);
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
        txtNames.clear();
        txtLastNames.clear();
        txtCurso.clear();
        txtSeccion.clear();
        txtIdentityDocument.clear();
        txtAge.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
    }

    private void clearAction() {
        action = "";
    }

    private void showFields(boolean value) {
        txtNames.setVisible(value);
        txtLastNames.setVisible(value);
        txtCurso.setVisible(value);
        txtSeccion.setVisible(value);
        txtIdentityDocument.setVisible(value);
        txtAge.setVisible(value);
        txtPhoneNumber.setVisible(value);
        txtEmail.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }
    private void refreshInformationGrid() {
        collectionParticipante = controllerParticipation.findAllParticipations();
        dataProvider = DataProvider.ofCollection(collectionParticipante);
        grid.setDataProvider(dataProvider);

    }
}
