package com.bionaturista.application.dto.compuesto;

import com.bionaturista.domain.entities.Producto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString
public class CompuestoDto {

    Integer idCompuesto;
    String nombreCompuesto;





}
