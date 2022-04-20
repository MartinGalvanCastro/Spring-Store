package com.marting.store.service;

import com.marting.store.entity.Supplier;
import com.marting.store.repository.ProductRepository;
import com.marting.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ServiceInterface<Supplier,Long> {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getById(Long id) throws EntityNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()) return  supplier.get();
        else throw new EntityNotFoundException("Supplier with Id: "+ id +" Not found");
    }

    @Override
    public Supplier create(Supplier newEntity) {
        return supplierRepository.save(newEntity);
    }

    @Override
    public Supplier update(Long id, Supplier entity) {
        if(supplierRepository.existsById(id)){
            entity.setId(id);
            return supplierRepository.save(entity);
        } else throw new EntityNotFoundException("Supplier with Id: "+ id +" Not found");
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
