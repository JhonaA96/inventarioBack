package com.example.inventario.serviceImpl;

import com.example.inventario.dao.ProductoDao;
import com.example.inventario.model.Producto;
import com.example.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public Producto save(Producto producto) {
        validarMayorCero(producto.getCantidad());
        validarMayorCero(producto.getPrecio().intValue());
        return productoDao.save(producto);
    }

    @Override
    public List<Producto> findAllProducts() {
        return productoDao.findAll();
    }

    @Override
    public Optional<Producto> findProductById(Long id) {
        validarProducto(id);
        Optional<Producto> producto = productoDao.findById(id);
        return producto;
    }

    @Override
    public void deleteById(Long id) {
        validarProducto(id);
        productoDao.deleteById(id);
    }

    @Override
    public Producto updateProduct(Long id, Producto producto){

        validarProducto(id);
        Optional<Producto> product = findProductById(id);

        product.get().setNombre(producto.getNombre());
        product.get().setPrecio(producto.getPrecio());
        product.get().setCantidad(producto.getCantidad());
        product.get().setEstado(producto.getEstado());
        product.get().setDescripcion(producto.getDescripcion());
        product.get().setCategoria(producto.getCategoria());

        return productoDao.save(product.get());
    }

    public void validarProducto(Long id){
        if(!productoDao.findById(id).isPresent()){
            throw new RuntimeException("El producto no existe");
        }
    }

    public void validarMayorCero(Integer num){
        if(num <= 0){
            throw new RuntimeException("El numero debe ser mayor a cero");
        }
    }
}
