package com.marting.store.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ServiceInterface<T> {

    List<T> getAll();
    T getById(Long id) throws EntityNotFoundException;
    T create(T newEntity);
    T update(Long id, T entity);
    void delete(Long id);

}
