package com.marting.store.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ServiceInterface<T,ID> {

    List<T> getAll();
    T getById(ID id) throws EntityNotFoundException;
    T create(T newEntity);
    T update(ID id, T entity);
    void delete(ID id);

}
