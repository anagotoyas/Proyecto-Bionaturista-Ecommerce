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
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("idCategoria")
    private Integer idCategoria;

    @NotNull
    @Size(min = 5, max = 50, message = "La categoria tiene como maximo 50 caracteres")
    @JsonProperty("nombreCategoria")
    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private String nombreCategoria;


    @JsonIgnore
    @OneToMany(mappedBy ="categoria")
    private List<Producto> producto;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    //@JsonManagedReference(value="cat")
    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }
}
