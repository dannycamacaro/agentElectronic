package com.agenda.electronic.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetalleeventosEntityPK implements Serializable {
    private Integer iddetalleeventos;
    private Integer institucionesIdinstituciones;
    private Integer eventoIdevento;

    @Column(name = "iddetalleeventos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Integer getIddetalleeventos() {
        return iddetalleeventos;
    }

    public void setIddetalleeventos(Integer iddetalleeventos) {
        this.iddetalleeventos = iddetalleeventos;
    }

    @Column(name = "instituciones_idinstituciones")
    @Id
    public Integer getInstitucionesIdinstituciones() {
        return institucionesIdinstituciones;
    }

    public void setInstitucionesIdinstituciones(Integer institucionesIdinstituciones) {
        this.institucionesIdinstituciones = institucionesIdinstituciones;
    }

    @Column(name = "evento_idevento")
    @Id
    public Integer getEventoIdevento() {
        return eventoIdevento;
    }

    public void setEventoIdevento(Integer eventoIdevento) {
        this.eventoIdevento = eventoIdevento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleeventosEntityPK that = (DetalleeventosEntityPK) o;
        return Objects.equals(iddetalleeventos, that.iddetalleeventos) &&
                Objects.equals(institucionesIdinstituciones, that.institucionesIdinstituciones) &&
                Objects.equals(eventoIdevento, that.eventoIdevento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetalleeventos, institucionesIdinstituciones, eventoIdevento);
    }
}
