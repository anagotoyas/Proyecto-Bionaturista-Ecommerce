package com.bionaturista.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_id_usuario"))
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_info_envio", nullable = false, foreignKey = @ForeignKey(name = "FK_id_info_envio"))
    private InfoEnvio infoEnvio;

    @NotNull
    @Column(name = "monto_pago",nullable = false)
    private float montoPago;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_pedido",nullable = false)
    private Date fechaPedido;

    @PrePersist
    public void onCreate() {
        fechaPedido = new Date();
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_entrega",nullable = true)
    private Date fechaEntrega;

    @NotNull
    @Size(min = 3, max = 50, message = "El método de pago debe ser mínimo 3 caracteres y máximo 50.")
    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false, foreignKey = @ForeignKey(name = "FK_id_estado"))
    private EstadoPedido estadoPedido;

    @NotNull
    @Column(name = "costo_envio",nullable = false)
    private float costoEnvio;

    @NotNull
    @Column(name = "subtotal",nullable = false)
    private float subtotal;
    
    @ManyToMany
    @JoinTable(name = "detalle_pedido", joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    Set<Producto> productosPedido = new LinkedHashSet<>();


}

