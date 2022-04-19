package com.marting.store.controller;

import com.marting.store.entity.Client;
import com.marting.store.entity.Order;
import com.marting.store.entity.Payment;
import com.marting.store.service.ClientService;
import com.marting.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/client")
@RestController
public class ClientController implements RESTController<Client> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService, OrderService orderService) {
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
            throw new EntityNotFoundException("Client with Id: " + id + " Not found");
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


    /**
     * *****************************
     * Client-Payment Controller
     *****************************/

    @GetMapping("/{idClient}/payment")
    @ResponseBody
    public List<Payment> getAllPayments(@PathVariable("idClient") Long idClient) throws EntityNotFoundException {
        return clientService.getAllPaymentMethods(idClient);
    }

    @GetMapping("/{idClient}/payment/{idPayment}")
    @ResponseBody
    public Payment getPayment(@PathVariable("idClient") Long idClient,
                              @PathVariable("idPayment") Long idPayment) throws EntityNotFoundException {
        return clientService.getPaymentById(idClient, idPayment);
    }

    @PostMapping("/{idClient}/payment")
    @ResponseBody
    public Payment createPayment(@PathVariable("idClient") Long idClient,
                                 @Valid @RequestBody Payment newPayment) {
        return clientService.createPayment(idClient, newPayment);
    }

    @PutMapping("/{idClient}/payment/{idPayment}")
    @ResponseBody
    public Payment updatePayment(@PathVariable("idClient") Long idClient,
                                 @PathVariable("idPayment") Long idPayment,
                                 @Valid @RequestBody Payment updatedPayment) {
        return clientService.updatePayment(idClient, idPayment, updatedPayment);
    }

    @DeleteMapping("/{idClient}/payment/{idPayment}")
    public void deletePayment(@PathVariable("idClient") Long idClient,
                              @PathVariable("idPayment") Long idPayment) {
        clientService.deletePayment(idClient, idPayment);
    }

    /**
     * *****************************
     * Client-Order Controller
     *****************************/
    @PostMapping("{idClient}/order")
    public Order createOrder(@PathVariable("idClient") long idClient,
                             @Valid @RequestBody Order newOrder) {

        return clientService.createOrder(idClient, newOrder);
    }

}
