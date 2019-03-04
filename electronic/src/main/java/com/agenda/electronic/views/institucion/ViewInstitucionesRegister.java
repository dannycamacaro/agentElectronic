package com.agenda.electronic.views.institucion;


import com.agenda.electronic.controller.ControllerInstituciones;
import com.agenda.electronic.entity.InstitucionesEntity;
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
@SpringView(name = ViewInstitucionesRegister.VIEW_NAME)
public class ViewInstitucionesRegister  extends VerticalLayout implements View {
    public static final String VIEW_NAME = "RegisterInstituciones";

    @Autowired
    ControllerInstituciones controllerInstituciones;
    
    //fields
    private TextField txtRif = new TextField(EnumLabel.RIF_LABEL.getLabel());
    private TextField txtName = new TextField(EnumLabel.INSTITUCION_LABEL.getLabel());
    private TextField txtDireccion = new TextField(EnumLabel.DIRECTION_LABEL.getLabel());
    private TextField txtPersonaContacto = new TextField(EnumLabel.PERSONA_CONTACTO_LABEL.getLabel());
    private TextField txtTelefonoPersonaContacto = new TextField(EnumLabel.TEL_PERSONA_LABEL.getLabel());
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
    private Panel principalPanel = new Panel("Mantenimiento de Institucion");
    private HorizontalLayout operationButtons = new HorizontalLayout();
    private HorizontalLayout operationButtonsFooter = new HorizontalLayout();
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);
    private ListDataProvider<InstitucionesEntity> dataProvider;
    private Grid<InstitucionesEntity> grid = new Grid<>();
    List<InstitucionesEntity> collectionInstituciones;
    private InstitucionesEntity institucionEntitySelected;
    private String action;

    public ViewInstitucionesRegister() {
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


    private void setPropertiesField() {

        txtRif.setRequiredIndicatorVisible(true);
        txtName.setRequiredIndicatorVisible(true);
        txtDireccion.setRequiredIndicatorVisible(true);
        txtPersonaContacto.setRequiredIndicatorVisible(true);
        txtTelefonoPersonaContacto.setRequiredIndicatorVisible(true);
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
                    addInstitucion();
                } else if (action.equalsIgnoreCase("edit")) {
                    updateFields();
                } else if (action.equalsIgnoreCase("delete")) {
                    deleteInstitucion();
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

    private void deleteInstitucion() {
        controllerInstituciones.delete(institucionEntitySelected);
    }

    private void updateFields() {
        institucionEntitySelected.setCorreo(txtEmail.getValue());
        institucionEntitySelected.setDireccion(txtDireccion.getValue());
        institucionEntitySelected.setTelefonopersona(txtTelefonoPersonaContacto.getValue());
        institucionEntitySelected.setPersonacontacto(txtPersonaContacto.getValue());
        institucionEntitySelected.setNombeinstitucion(txtName.getValue());
        institucionEntitySelected.setRif(txtRif.getValue());
        institucionEntitySelected.setTelefono(txtPhoneNumber.getValue());
        institucionEntitySelected.setTelefonopersona(txtTelefonoPersonaContacto.getValue());
        controllerInstituciones.update(institucionEntitySelected);
    }

    private void addInstitucion() {
        InstitucionesEntity entity = new InstitucionesEntity();
        entity.setCorreo(txtEmail.getValue());
        entity.setDireccion(txtDireccion.getValue());
        entity.setTelefonopersona(txtTelefonoPersonaContacto.getValue());
        entity.setPersonacontacto(txtPersonaContacto.getValue());
        entity.setNombeinstitucion(txtName.getValue());
        entity.setRif(txtRif.getValue());
        entity.setTelefono(txtPhoneNumber.getValue());
        entity.setTelefonopersona(txtTelefonoPersonaContacto.getValue());
        controllerInstituciones.save(entity);
    }

    private void showFields(boolean value) {
        txtEmail.setVisible(value);
        txtDireccion.setVisible(value);
        txtTelefonoPersonaContacto.setVisible(value);
        txtPersonaContacto.setVisible(value);
        txtName.setVisible(value);
        txtRif.setVisible(value);
        txtPhoneNumber.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private void clearAction() {
        action="";
    }

    private void buildFields() {
        txtEmail.setPlaceholder("RamonSuarez@conors.com");
        fieldsLayout.addComponents(txtRif, txtName, txtDireccion, txtPersonaContacto, txtTelefonoPersonaContacto, txtPhoneNumber, txtEmail);
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
        txtEmail.clear();
        txtDireccion.clear();
        txtTelefonoPersonaContacto.clear();
        txtPersonaContacto.clear();
        txtName.clear();
        txtRif.clear();
        txtPhoneNumber.clear();
    }

    private void enableFields(boolean value) {
        txtEmail.setEnabled(value);
        txtDireccion.setEnabled(value);
        txtTelefonoPersonaContacto.setEnabled(value);
        txtPersonaContacto.setEnabled(value);
        txtName.setEnabled(value);
        txtRif.setEnabled(value);
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

    private void createGrid() {
        List<InstitucionesEntity> collectionInstituciones = controllerInstituciones.findAllInstituciones();
        dataProvider = DataProvider.ofCollection(collectionInstituciones);

        grid.setEnabled(true);
        grid.addColumn(InstitucionesEntity::getNombeinstitucion).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        grid.addColumn(InstitucionesEntity::getPersonacontacto).setCaption(EnumLabel.NAMES_LABEL.getLabel());
        grid.addColumn(InstitucionesEntity::getTelefonopersona).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<InstitucionesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<InstitucionesEntity> event) {
                institucionEntitySelected = event.getItem();
                txtDireccion.setValue(institucionEntitySelected.getDireccion());
                txtEmail.setValue(institucionEntitySelected.getCorreo());
                txtName.setValue(institucionEntitySelected.getNombeinstitucion());
                txtPersonaContacto.setValue(institucionEntitySelected.getPersonacontacto());
                txtPhoneNumber.setValue(institucionEntitySelected.getTelefono());
                txtRif.setValue(institucionEntitySelected.getRif());
                txtTelefonoPersonaContacto.setValue(institucionEntitySelected.getTelefonopersona());

            }
        });
    }


    private void visibleGridLayout(boolean visible) {
        fieldsLayout.setVisible(visible);

    }
    private void refreshInformationGrid() {
        collectionInstituciones = controllerInstituciones.findAllInstituciones();
        dataProvider = DataProvider.ofCollection(collectionInstituciones);
        grid.setDataProvider(dataProvider);

    }
}
