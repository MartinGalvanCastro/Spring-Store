package com.marting.store.service;

import com.marting.store.entity.Product;
import com.marting.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ServiceInterface<Product,Long> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) return product.get();
        else throw new EntityNotFoundException("Product with Id: " + id + " Not found");
    }

    @Override
    public Product create(Product newEntity) {
        return productRepository.save(newEntity);
    }

    @Override
    public Product update(Long id, Product entity) {
        if (productRepository.existsById(id)) {
            entity.setId(id);
            return productRepository.save(entity);
        } else throw new EntityNotFoundException("Product with Id: " + id + " Not found");

    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
