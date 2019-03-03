package com.agenda.electronic.views;


import com.agenda.electronic.enums.EnumMessages;
import com.agenda.electronic.navigator.UniverseNavigator;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path = ViewLogin.APP_ROOT)
@Title("Agenda Electronica")
@SpringView
public class ViewLogin extends UI implements View {
    public static final String APP_ROOT = "/project";

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private SpringViewProvider viewProvider;

    Panel panelPrincipal = new Panel();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        panelPrincipal.setSizeFull();
        HorizontalLayout root = new HorizontalLayout();
        VerticalLayout visitantes = new VerticalLayout();
        ExternalResource externalResource = new ExternalResource("VAADIN/img/logo.png");
        Image image = new Image();
        image.setSource(externalResource);
        image.setResponsive(true);
        image.setSizeFull();
        image.setStyleName(ValoTheme.LAYOUT_CARD);
        visitantes.setStyleName(ValoTheme.PANEL_WELL);
        visitantes.addComponent(image);
        visitantes.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        VerticalLayout login = new VerticalLayout();
        visitantes.setHeight("100%");
        visitantes.setWidth("100%");

        login.setHeight("100%");
        login.setWidth("100%");

        root.addStyleName(ValoTheme.PANEL_BORDERLESS);
        //Formulario de login
        FormLayout loginForm = new FormLayout();
        loginForm.addStyleName(ValoTheme.LABEL_COLORED);
        Label lblTitle = new Label(EnumMessages.MESSAGES_LOGIN.getMessage());
        lblTitle.addStyleName(ValoTheme.LABEL_H1);
        lblTitle.setSizeFull();
        loginForm.setWidth("600px");
        loginForm.setHeight("200px");
        loginForm.addStyleNames(ValoTheme.PANEL_BORDERLESS);
        loginForm.setStyleName("Background-color: white");

        TextField txtUser = new TextField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtUser.setCaption("Usuario");
        PasswordField txtPassword = new PasswordField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtPassword.setCaption("Contraseña");

        txtUser.setValue("");
        txtPassword.setValue("");
        Button btnEntrar = new Button();
        btnEntrar.setCaption("Iniciar sesion");
        btnEntrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //UsuariosEntity entity = controllerLogin.validateLogin(txtUser.getValue(), txtPassword.getValue());
                iniNavigator();
            }
        });
        btnEntrar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEntrar.setWidth("200px");

        loginForm.addComponent(lblTitle);
        loginForm.addComponent(txtUser);
        loginForm.addComponent(txtPassword);
        loginForm.addComponent(btnEntrar);
        login.addComponent(loginForm);
        login.setStyleName("Background-color: white");
        root.addComponent(visitantes);
        root.addComponent(login);
        root.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
        root.setSizeFull();
        root.setStyleName("Background-color: white");

        //Alineamiento de los componentes
        loginForm.setComponentAlignment(lblTitle, Alignment.MIDDLE_CENTER);
        root.addStyleNames(ValoTheme.LAYOUT_WELL);
        panelPrincipal.setContent(root);
        panelPrincipal.setStyleName("Background-color: white");
        setContent(panelPrincipal);

    }

    private void iniNavigator() {
        UniverseNavigator navigator = new UniverseNavigator(this, this);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(viewProvider);
        navigator.navigateTo(ViewMenu.VIEW_NAME);
    }
}
