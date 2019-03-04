package com.agenda.electronic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detalleeventos", schema = "dbagendaelectronica")
@IdClass(DetalleeventosEntityPK.class)
public class DetalleeventosEntity {
    private Integer iddetalleeventos;
    private Integer institucionesIdinstituciones;
    private Integer eventoIdevento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalleeventos")
    public Integer getIddetalleeventos() {
        return iddetalleeventos;
    }

    public void setIddetalleeventos(Integer iddetalleeventos) {
        this.iddetalleeventos = iddetalleeventos;
    }

    @Id
    @Column(name = "instituciones_idinstituciones")
    public Integer getInstitucionesIdinstituciones() {
        return institucionesIdinstituciones;
    }

    public void setInstitucionesIdinstituciones(Integer institucionesIdinstituciones) {
        this.institucionesIdinstituciones = institucionesIdinstituciones;
    }

    @Id
    @Column(name = "evento_idevento")
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
        DetalleeventosEntity that = (DetalleeventosEntity) o;
        return Objects.equals(iddetalleeventos, that.iddetalleeventos) &&
                Objects.equals(institucionesIdinstituciones, that.institucionesIdinstituciones) &&
                Objects.equals(eventoIdevento, that.eventoIdevento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetalleeventos, institucionesIdinstituciones, eventoIdevento);
    }
}
