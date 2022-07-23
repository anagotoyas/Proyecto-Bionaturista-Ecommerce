package com.bionaturista.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre debe ser mínimo 3 caracteres y máximo 100.")
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @NotNull
    @Size(min = 6, max = 25, message = "La contraseña debe tener como mínimo 6 caracteres")
    @Column(name = "contrasena_usuario", nullable = false, length = 25)
    private String contrasenaUsuario;

    @NotNull
    @Email(message = "Formato incorrecto del correo electrónico")
    @Column(name = "correo_usuario", nullable = false, unique = true)
    private String correoUsuario;

    @Size(max = 50, message = "La dirección del usuario debe tener menos de 50 caracteres")
    @Column(name = "direccion_usuario", length = 50)
    private String direccionUsuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_id_rol"))
    private Rol rol;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(name = "carritos", joinColumns = @JoinColumn(name = "id_carrito"),inverseJoinColumns = @JoinColumn(name = "id_producto"))
    Set<Producto> carritoCompras = new LinkedHashSet<>();

    //Agregé una nueva lista de notificaciones, esta solo será usada por los administradores
    //Se usará para notificar cada vez que se haya agregado un nuevo pedido

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificaciones;


}

