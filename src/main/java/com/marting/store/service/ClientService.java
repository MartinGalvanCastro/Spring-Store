package com.marting.store.service;

import com.marting.store.entity.Client;
import com.marting.store.entity.Order;
import com.marting.store.entity.Payment;
import com.marting.store.repository.ClientRepository;
import com.marting.store.repository.OrderRepository;
import com.marting.store.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ServiceInterface<Client> {


    private final ClientRepository clientRepository;

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) return client.get();
        else throw new EntityNotFoundException("Client with Id: " + id + " Not found");
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

    public List<Payment> getAllPaymentMethods(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) return client.get().getPaymentMethods();
        else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }

    public Payment getPaymentById(Long clientId, Long paymentId) throws EntityNotFoundException {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            Optional<Payment> payment = client.get().getPaymentMethod(paymentId);
            if (payment.isPresent()) return payment.get();
            else throw new EntityNotFoundException("Payment with Id: " + paymentId + " Not found");
        } else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }

    public Payment createPayment(Long clientId, Payment newPayment) throws  EntityNotFoundException{
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            Payment savedPaymentMethod = paymentRepository.save(newPayment);
            client.get().addPaymentMehtod(savedPaymentMethod);
            clientRepository.save(client.get());
            return savedPaymentMethod;
        } else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }

    public Payment updatePayment(Long clientId, Long paymentId, Payment updatedPayment) throws EntityNotFoundException {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            Client clientFound = client.get();
            Optional<Payment> oldPayment = clientFound.getPaymentMethod(paymentId);
            if(oldPayment.isPresent()){
                updatedPayment.setId(paymentId);
                int idx = clientFound.getPaymentMethods().indexOf(updatedPayment);
                clientFound.getPaymentMethods().set(idx,updatedPayment);
                return updatedPayment;
            } else throw new EntityNotFoundException("Payment with Id: " + paymentId + " Not found");
        }else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }

    public void deletePayment(Long clientId, Long paymentId) throws EntityNotFoundException{
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            Optional<Payment> payment = client.get().getPaymentMethod(paymentId);
            if(payment.isPresent()) client.get().getPaymentMethods().remove(payment.get());
            else throw new EntityNotFoundException("Payment with Id: " + paymentId + " Not found");
        }else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }

    public Order createOrder(Long clientId, Order newOrder) throws EntityNotFoundException
    {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            Order savedOrder = orderRepository.save(newOrder);
            client.get().addOrder(savedOrder);
            clientRepository.save(client.get());
            return  savedOrder;
        } else throw new EntityNotFoundException("Client with Id: " + clientId + " Not found");
    }


}
