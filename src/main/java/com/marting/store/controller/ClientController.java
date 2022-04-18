package com.marting.store.controller;

import com.marting.store.entity.Client;
import com.marting.store.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping("/api/client")
@RestController
public class ClientController implements RESTController<Client> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<Client> get() {
        return clientService.getAll();
    }

    @Override
    public Client get(Long id) throws EntityNotFoundException {
        try {
            return clientService.getById(id);
        } catch (HttpMessageNotWritableException e) {
            throw new EntityNotFoundException("Client with Id: " + id +" Not found");
        }
    }

    @Override
    public Client create(Client newEntity) {
        return clientService.create(newEntity);
    }

    @Override
    public Client update(Long id, Client updatedEntity) {
        return clientService.update(id, updatedEntity);
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }
}
