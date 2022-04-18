package com.marting.store.controller;

import com.marting.store.entity.Supplier;
import com.marting.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping("/api/supplier")
@RestController
public class SupplierController implements RESTController<Supplier> {


    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * GET request for returning the list of T entities
     *
     * @return List of T entities.
     */
    @Override
    public List<Supplier> get() {
        return supplierService.getAll();
    }

    /**
     * GET request for returning a T entity by its ID
     *
     * @param id The ID of the Entity
     * @return T entity if the entity is found
     * @throws EntityNotFoundException if the T entity is not found
     */
    @Override
    public Supplier get(Long id) throws EntityNotFoundException {
        try{
            return supplierService.getById(id);
        } catch (HttpMessageNotWritableException e){
            throw new EntityNotFoundException("Supplier with Id: "+ id + " Not found");
        }
    }

    /**
     * POST request for creating a new Entity of type T
     *
     * @param newEntity new Entity of type T.
     * @return the new entity with an assigned ID
     * @throws MethodArgumentNotValidException if the entity field validation fails
     */
    @Override
    public Supplier create(Supplier newEntity) throws MethodArgumentNotValidException {
        return supplierService.create(newEntity);
    }

    /**
     * PUT request for updating an Entity of type T
     *
     * @param id            id of the entity to be updated
     * @param updatedEntity entity POJO for being updated
     * @return the entity updated
     * @throws EntityNotFoundException if it is not found an entity with the given ID
     */
    @Override
    public Supplier update(Long id, Supplier updatedEntity) throws EntityNotFoundException {
        return supplierService.update(id,updatedEntity);
    }

    /**
     * DELETE request for deleting an Entity of type T
     *
     * @param id id of the entity to be deleted
     */
    @Override
    public void delete(Long id) {
        supplierService.delete(id);
    }
}
