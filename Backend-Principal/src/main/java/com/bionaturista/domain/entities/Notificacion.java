package com.bionaturista.domain.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
public class Notificacion {
    @Id
    @Column(name = "id_notificacion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacion;

    @NotNull
    @Column(name = "contenido", nullable = false)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_id_usuario"))
    private Usuario usuario;

}
