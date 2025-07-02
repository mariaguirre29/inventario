package com.inventario.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class InventarioDTO extends RepresentationModel<InventarioDTO> {
    private String nombreProducto;
    private Integer cantidad;
    private String descripcion;
} 