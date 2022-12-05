package com.example.inventario.controller;

import com.example.inventario.model.Producto;
import com.example.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listar")
    public Iterable<Producto> consultarProductos(){
        Iterable<Producto> productos = StreamSupport.stream(productoService.findAllProducts().spliterator(), false)
                .collect(Collectors.toList());
        return productos;
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Producto> consultarProducto(@PathVariable Long id){
        return productoService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.save(producto));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id){
        productoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.updateProduct(id, producto));
    }
}
