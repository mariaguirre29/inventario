package com.inventario.controllers;

import com.inventario.dto.InventarioDTO;
import com.inventario.models.Inventario;
import com.inventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Inventario", description = "API para gestión de inventario")
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @Operation(summary = "Obtener todos los productos de inventario")
    @GetMapping
    public List<Inventario> getAll() {
        List<Inventario> inventarios = inventarioService.getAll();
        for (Inventario inv : inventarios) {
            Link selfLink = Link.of("http://localhost:8888/api/inventario/" + inv.getId()).withSelfRel();
            inv.add(selfLink);
        }
        return inventarios;
    }

    @Operation(summary = "Obtener un producto de inventario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        return inventarioService.getById(id)
                .map(inv -> {
                    Link selfLink = Link.of("http://localhost:8888/api/inventario/" + inv.getId()).withSelfRel();
                    inv.add(selfLink);
                    return ResponseEntity.ok(inv);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un producto de inventario")
    @PostMapping
    public Inventario create(@RequestBody InventarioDTO dto) {
        Inventario inv = inventarioService.create(dto);
        Link selfLink = Link.of("http://localhost:8888/api/inventario/" + inv.getId()).withSelfRel();
        inv.add(selfLink);
        return inv;
    }

    @Operation(summary = "Actualizar un producto de inventario")
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Long id, @RequestBody InventarioDTO dto) {
        return inventarioService.update(id, dto)
                .map(inv -> {
                    Link selfLink = Link.of("http://localhost:8888/api/inventario/" + inv.getId()).withSelfRel();
                    inv.add(selfLink);
                    return ResponseEntity.ok(inv);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un producto de inventario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (inventarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 