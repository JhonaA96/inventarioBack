package com.example.inventario.service;

import com.example.inventario.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoService {

    List<Producto> findAllProducts();
    Optional<Producto> findProductById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    Producto updateProduct(Long id, Producto producto);

}
