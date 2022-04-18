package com.marting.store.service;

import com.marting.store.entity.Order;
import com.marting.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceInterface<Order> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public  Order getById(Long id) throws EntityNotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) return order.get();
        else throw new EntityNotFoundException("Order with Id: " + id + " Not found");
    }

    @Override
    public Order create(Order newEntity) {
        return orderRepository.save(newEntity);
    }

    @Override
    public Order update(Long id, Order entity) {
        if (orderRepository.existsById(id)) {
            entity.setId(id);
            return orderRepository.save(entity);
        } else throw new EntityNotFoundException("Order with Id: " + id + " Not found");
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
