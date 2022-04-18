package com.marting.store.controller;

import com.marting.store.entity.Order;
import com.marting.store.entity.Product;
import com.marting.store.service.OrderService;
import com.marting.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController implements RESTController<Product>{


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public List<Product> get() {
        return productService.getAll();
    }

    @Override
    public Product get(Long id) throws EntityNotFoundException {
        try{
            return productService.getById(id);
        }catch (HttpMessageNotWritableException e){
            throw new EntityNotFoundException("Product with Id: "+ id+ " Not found");
        }
    }

    @Override
    public Product create(Product newEntity) {
        return productService.create(newEntity);
    }

    @Override
    public Product update(Long id, Product updatedEntity) {
        return productService.update(id,updatedEntity);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

}
