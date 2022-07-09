package com.bionaturista.domain.entities;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@ToString
@Table(name = "compuestos")

public class Compuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompuesto;

    @NotNull
    @Size(min=3,max=100, message="El nombre debe ser mínimo 3 caracteres y máximo 100.")
    @Column(name = "nombreCompuesto", nullable = false)
    private String nombreCompuesto;

    @JsonIgnore
    @OneToMany(mappedBy ="compuesto")
    private List<Producto> producto;

    public Integer getIdCompuesto() {
        return idCompuesto;
    }

    public void setIdCompuesto(Integer idCompuesto) {
        this.idCompuesto = idCompuesto;
    }

    public String getNombreCompuesto() {
        return nombreCompuesto;
    }

    public void setNombreCompuesto(String nombreCompuesto) {
        this.nombreCompuesto = nombreCompuesto;
    }

    //@JsonManagedReference(value="comp")
    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }
}
