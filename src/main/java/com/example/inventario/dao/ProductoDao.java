package com.example.inventario.dao;

import com.example.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Long> {


}