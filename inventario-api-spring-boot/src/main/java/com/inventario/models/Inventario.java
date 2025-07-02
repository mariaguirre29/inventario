package com.inventario.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class Inventario extends RepresentationModel<Inventario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProducto;
    private Integer cantidad;
    private String descripcion;
} 