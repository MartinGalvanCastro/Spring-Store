package com.marting.store.service;

import com.marting.store.entity.Client;
import com.marting.store.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ServiceInterface<Client> {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
       Optional<Client> client = clientRepository.findById(id);
       if(client.isPresent()) return client.get();
       else throw new EntityNotFoundException("Client with Id: " + id +" Not found");
    }

    @Override
    public Client create(Client newEntity) {
        return clientRepository.save(newEntity);
    }

    @Override
    public Client update(Long id, Client entity) {
        if (clientRepository.existsById(id)) {
            entity.setId(id);
            return clientRepository.save(entity);
        } else throw new EntityNotFoundException("Client with Id: " + id + " Not found");
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
