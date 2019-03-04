package com.agenda.electronic.views.facilitador;

import com.agenda.electronic.controller.ControllerFacilitador;
import com.agenda.electronic.entity.FacilitadoresEntity;
import com.agenda.electronic.entity.FacilitadoresEntity;
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
import java.util.List;

@UIScope
@SpringView(name = ViewFacilitadorRegister.VIEW_NAME)
public class ViewFacilitadorRegister extends VerticalLayout implements View {
    public static final String VIEW_NAME = "RegisterFacilitador";
    
    @Autowired
    ControllerFacilitador controllerFacilitador;
    //fields
    private TextField txtDocumento = new TextField(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
    private TextField txtNames = new TextField(EnumLabel.NAMES_LABEL.getLabel());
    private TextField txtLastNames = new TextField(EnumLabel.LAST_NAME_LABEL.getLabel());
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
    private Panel principalPanel = new Panel("Mantenimiento de Facilitador");
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<FacilitadoresEntity> dataProvider;
    private Grid<FacilitadoresEntity> grid = new Grid<>();
    List<FacilitadoresEntity> collectionFacilitadores;
    private FacilitadoresEntity facilitadoresEntitySelected;
    private String action;

    public ViewFacilitadorRegister() {
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

    private void showFields(boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private void createGrid() {
        List<FacilitadoresEntity> collectionFacilitador = controllerFacilitador.findAllFacilitador();
        dataProvider = DataProvider.ofCollection(collectionFacilitador);

        grid.setEnabled(true);
        grid.addColumn(FacilitadoresEntity::getIddocumento).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        grid.addColumn(FacilitadoresEntity::getNombres).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        grid.addColumn(FacilitadoresEntity::getApellidos).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        grid.addColumn(FacilitadoresEntity::getTelefono).setCaption(EnumLabel.PHONE_NUMBER_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<FacilitadoresEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<FacilitadoresEntity> event) {
                facilitadoresEntitySelected = event.getItem();
                txtDocumento.setValue(facilitadoresEntitySelected.getIddocumento());
                txtEmail.setValue(facilitadoresEntitySelected.getCorreo());
                txtLastNames.setValue(facilitadoresEntitySelected.getApellidos());
                txtNames.setValue(facilitadoresEntitySelected.getNombres());
                txtPhoneNumber.setValue(facilitadoresEntitySelected.getTelefono());

            }
        });
    }

    private void clearAction() {
        action = "";
    }

    private void setPropertiesField() {

        txtDocumento.setRequiredIndicatorVisible(true);
        txtNames.setRequiredIndicatorVisible(true);
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
                    addFacilitador();
                } else if (action.equalsIgnoreCase("edit")) {
                    updateFields();
                    editFacilitador(facilitadoresEntitySelected);
                } else if (action.equalsIgnoreCase("delete")) {
                    deleteFacilitador(facilitadoresEntitySelected);
                    processDeleteUser();
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

    private void deleteFacilitador(FacilitadoresEntity facilitadoresEntitySelected) {
        controllerFacilitador.delete(facilitadoresEntitySelected);
    }

    private void editFacilitador(FacilitadoresEntity facilitadoresEntitySelected) {
        controllerFacilitador.update(facilitadoresEntitySelected);
    }

    private void updateFields() {
        facilitadoresEntitySelected.setCorreo(txtEmail.getValue());
        facilitadoresEntitySelected.setTelefono(txtPhoneNumber.getValue());
        facilitadoresEntitySelected.setApellidos(txtLastNames.getValue());
        facilitadoresEntitySelected.setNombres(txtNames.getValue());
        facilitadoresEntitySelected.setIddocumento(txtDocumento.getValue());

    }

    private void addFacilitador() {
        FacilitadoresEntity facilitadoresEntity = new FacilitadoresEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            try {
                facilitadoresEntity.setIddocumento(txtDocumento.getValue());
                facilitadoresEntity.setNombres(txtNames.getValue());
                facilitadoresEntity.setApellidos(txtLastNames.getValue());
                facilitadoresEntity.setTelefono(txtPhoneNumber.getValue());
                facilitadoresEntity.setCorreo(txtEmail.getValue());
                controllerFacilitador.save(facilitadoresEntity);
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void buildFields() {
        txtEmail.setPlaceholder("RamonSuarez@conors.com");
        fieldsLayout.addComponents(txtDocumento, txtNames,txtLastNames, txtPhoneNumber, txtEmail);
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
                    showFields(true);
                    action = "edit";
                }
            }
        });
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    showFields(true);
                    action = "delete";
                    enableFields(false);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void clearFields() {
        txtEmail.clear();
        txtNames.clear();
        txtDocumento.clear();
        txtPhoneNumber.clear();
        txtLastNames.clear();
    }

    private void enableFields(boolean value) {
        txtEmail.setEnabled(value);
        txtNames.setEnabled(value);
        txtDocumento.setEnabled(value);
        txtPhoneNumber.setEnabled(value);
        txtLastNames.setEnabled(value);
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
        if (isValidationFieldEmpty(txtDocumento)) {
            Notification.show("Debe llenar el campo Usuario", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtNames)) {
            Notification.show("Debe llenar el campo Nombre", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtPhoneNumber)) {
            Notification.show("Debe llenar el campo Numero Telefonico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtEmail)) {
            Notification.show("Debe llenar el campo Email", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyString(txtNames.getValue())) {
            Notification.show("Nombre solo puede ser letras", Notification.Type.ERROR_MESSAGE);
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
        txtDocumento.clear();
        txtNames.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
    }
    private void refreshInformationGrid() {
        collectionFacilitadores = controllerFacilitador.findAllFacilitador();
        dataProvider = DataProvider.ofCollection(collectionFacilitadores);
        grid.setDataProvider(dataProvider);

    }

}

