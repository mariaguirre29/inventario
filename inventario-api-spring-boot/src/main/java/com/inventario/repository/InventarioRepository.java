package com.inventario.repository;

import com.inventario.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
} 