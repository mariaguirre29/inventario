package com.inventario.services;

import com.inventario.dto.InventarioDTO;
import com.inventario.models.Inventario;
import com.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> getById(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario create(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setNombreProducto(dto.getNombreProducto());
        inventario.setCantidad(dto.getCantidad());
        inventario.setDescripcion(dto.getDescripcion());
        return inventarioRepository.save(inventario);
    }

    public Optional<Inventario> update(Long id, InventarioDTO dto) {
        return inventarioRepository.findById(id).map(inventario -> {
            inventario.setNombreProducto(dto.getNombreProducto());
            inventario.setCantidad(dto.getCantidad());
            inventario.setDescripcion(dto.getDescripcion());
            return inventarioRepository.save(inventario);
        });
    }

    public boolean delete(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 