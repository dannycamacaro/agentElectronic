package com.agenda.electronic.views;

import com.agenda.electronic.navigator.UniverseNavigator;
import com.agenda.electronic.views.event.ViewConsultEvent;
import com.agenda.electronic.views.event.ViewRegisterEvent;
import com.agenda.electronic.views.facilitador.ViewFacilitadorRegister;
import com.agenda.electronic.views.institucion.ViewInstitucionesRegister;
import com.agenda.electronic.views.participation.ViewParticipationRegister;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

@UIScope

@Title("Menu")
@SpringView(name = ViewMenu.VIEW_NAME, ui = ViewLogin.class)
public class ViewMenu extends VerticalLayout implements View {
    public static final String VIEW_NAME = "menu";
    MenuBar mainMenu;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (mainMenu == null) {
            mainMenu = buildMenu();
            addComponent(mainMenu);
        } else {
            addComponent(mainMenu);
        }
        this.setWidth("100%");
        this.setHeightUndefined();
        ExternalResource externalResource = new ExternalResource("VAADIN/img/menuLogo.jpeg");
        Image image = new Image();
        image.setSource(externalResource);
        image.setResponsive(true);
        image.setSizeFull();
        addComponent(image);
        this.setComponentAlignment(mainMenu, Alignment.TOP_CENTER);
    }

    public static MenuBar buildMenu() {
        MenuBar mainMenu = new MenuBar();

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem mantenimientoEventos = mainMenu.addItem("Mantenimiento de Eventos", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem registrarEventos = mantenimientoEventos.addItem("Mantenimiento de Evento", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewRegisterEvent.VIEW_NAME);
            }
        });
        MenuBar.MenuItem consultarEventos = mantenimientoEventos.addItem("Consultar Eventos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewConsultEvent.VIEW_NAME);
            }
        });
        MenuBar.MenuItem editarEventos = mantenimientoEventos.addItem("Detalle de Evento", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem mantenimientoInstituciones = mainMenu.addItem("Mantenimiento de Instituciones", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem registrarInstituciones = mantenimientoInstituciones.addItem("Registrar Instituciones", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewInstitucionesRegister.VIEW_NAME);
            }
        });
        MenuBar.MenuItem consultarInstituciones = mantenimientoInstituciones.addItem("Consultar Instituciones", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });
        MenuBar.MenuItem editarInstituciones = mantenimientoInstituciones.addItem("Editar Instituciones", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem mantenimientoDeParticipantes = mainMenu.addItem("Mantenimiento de Participantes", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem registrarParticipantes = mantenimientoDeParticipantes.addItem("Registrar Participantes", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewParticipationRegister.VIEW_NAME);
            }
        });
        MenuBar.MenuItem consultarParticipantes = mantenimientoDeParticipantes.addItem("Consultar Participantes", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });
        MenuBar.MenuItem editarParticipantes = mantenimientoDeParticipantes.addItem("Editar Participantes", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem mantenimientoFacilitadores = mainMenu.addItem("Mantenimiento de Facilitadores", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem registrarFacilitadores = mantenimientoFacilitadores.addItem("Registrar Facilitadores", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewFacilitadorRegister.VIEW_NAME);
            }
        });
        MenuBar.MenuItem consultarFacilitadores = mantenimientoFacilitadores.addItem("Consultar Facilitadores", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });
        MenuBar.MenuItem editarFacilitadores = mantenimientoFacilitadores.addItem("Editar Facilitadores", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });

        MenuBar.MenuItem cerrarSession = mainMenu.addItem("Cerrar Session", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem salir = cerrarSession.addItem("Salir", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //UniverseNavigator.navigate(EventViews.VIEW_NAME);
            }
        });

        return mainMenu;
    }

}
