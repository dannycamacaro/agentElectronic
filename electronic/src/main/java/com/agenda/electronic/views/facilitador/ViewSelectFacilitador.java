package com.agenda.electronic.views.facilitador;

import com.agenda.electronic.entity.EventoEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = ViewSelectFacilitador.VIEW_NAME)
public class ViewSelectFacilitador  extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ViewSelectFacilitador";
    EventoEntity eventoEntitySelected;

    private void buildForm() {

    }
    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute(EventoEntity.class) != null) {
            eventoEntitySelected = this.getUI().getSession().getAttribute(EventoEntity.class);
            buildForm();
        }
    }
}
