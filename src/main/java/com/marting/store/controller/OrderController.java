package com.marting.store.controller;

import com.marting.store.entity.Order;
import com.marting.store.entity.OrderProduct;
import com.marting.store.entity.Product;
import com.marting.store.service.OrderProductService;
import com.marting.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/order")
@RestController
public class OrderController implements RESTController<Order> {


    private final OrderService orderService;

    private final OrderProductService orderProductService;

    @Autowired
    public OrderController(OrderService orderService,
                           OrderProductService orderProductService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }


    /**
     * GET request for returning the list of T entities
     *
     * @return List of T entities.
     */
    @Override
    public List<Order> get() {
        return orderService.getAll();
    }

    /**
     * GET request for returning a T entity by its ID
     *
     * @param id The ID of the Entity
     * @return T entity if the entity is found
     * @throws EntityNotFoundException if the T entity is not found
     */
    @Override
    public Order get(Long id) throws EntityNotFoundException {
        try{
            return orderService.getById(id);
        } catch (HttpMessageNotWritableException e){
            throw new EntityNotFoundException("Order with Id: "+id + " Not found");
        }
    }

    /**
     * POST request for creating a new Entity of type T
     *
     * @throws MethodArgumentNotValidException if the entity field validation fails
     */
    @Override
    public Order create(Order newEntity) throws HttpRequestMethodNotSupportedException {
        throw new HttpRequestMethodNotSupportedException("Method not supported");
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
    public Order update(Long id, Order updatedEntity) throws EntityNotFoundException {
        return orderService.update(id,updatedEntity);
    }

    /**
     * DELETE request for deleting an Entity of type T
     *
     * @param id id of the entity to be deleted
     */
    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @GetMapping("/{orderId}/product")
    public List<Product> getProductsOfOrder(@PathVariable("orderId") Long orderId){
        Order order = orderService.getById(orderId);
        List<Product> productList = new ArrayList<>();
        for (OrderProduct orderProduct: order.getOrderProductList()) {
            productList.add(orderProduct.getProduct());
        }
        return productList;
    }

    @PostMapping("/{orderId}/product/{productId}")
    public Order addProduct(@PathVariable("orderId") Long orderId,
                            @PathVariable("productId") Long productId,
                            @Valid @RequestBody OrderProduct orderProduct){
        OrderProduct orderProductSaved = orderProductService.createOrderProduct(orderId,productId,orderProduct);
        return  orderProductSaved.getOrder();
    }


}