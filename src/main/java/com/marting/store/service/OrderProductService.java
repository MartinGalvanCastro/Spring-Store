package com.marting.store.service;

import com.marting.store.entity.Order;
import com.marting.store.entity.OrderProduct;
import com.marting.store.entity.Product;
import com.marting.store.entity.embeeded.OrderProductId;
import com.marting.store.repository.OrderProductRepository;
import com.marting.store.repository.OrderRepository;
import com.marting.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService implements ServiceInterface<OrderProduct, OrderProductId> {

    private final OrderProductRepository orderProductRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository,
                               OrderRepository orderRepository,
                               ProductRepository productRepository) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderProduct> getAll() {
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct getById(OrderProductId orderProductId) throws EntityNotFoundException {
        Optional<OrderProduct> orderProduct = orderProductRepository.findById(orderProductId);
        if(orderProduct.isPresent()) return orderProduct.get();
        else throw new EntityNotFoundException("OrderProduct with Id: "+ orderProductId + " Not found");
    }

    public OrderProduct createOrderProduct(Long orderId, Long productId, OrderProduct opOnlyQuantity) throws EntityNotFoundException{
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        if(order.isEmpty()){
            throw new EntityNotFoundException("Order with Id: "+ orderId + " Not found");
        }if(product.isEmpty()){
            throw new EntityNotFoundException("Product with Id: "+ orderId + " Not found");
        }
        OrderProduct finalOrderProduct = new OrderProduct(order.get(),product.get(),opOnlyQuantity.getQuantity());
        finalOrderProduct.updatePrice();
        return create(finalOrderProduct);
    }

    @Override
    public OrderProduct create(OrderProduct newEntity) {
        //Order order = newEntity.getOrder();
        //Product product = newEntity.getProduct();
        //order.addOrderProduct(newEntity);
        //product.addOrderProduct(newEntity);
        return orderProductRepository.save(newEntity);
    }

    @Override
    public OrderProduct update(OrderProductId orderProductId, OrderProduct entity) {
        Optional<OrderProduct> orderProduct = orderProductRepository.findById(orderProductId);
        if(orderProduct.isPresent()) {
            entity.setOrder(orderProduct.get().getOrder());
            entity.setProduct(orderProduct.get().getProduct());
            return orderProductRepository.save(entity);
        }
        else throw new EntityNotFoundException("OrderProduct with Id: "+ orderProductId + " Not found");
    }

    @Override
    public void delete(OrderProductId orderProductId) {
        Optional<OrderProduct> orderProduct = orderProductRepository.findById(orderProductId);
        if(orderProduct.isPresent()){
            OrderProduct orderProductFound = orderProduct.get();
            orderProductFound.getOrder().removeOrderProduct(orderProductFound);
            orderProductFound.getProduct().removeOrderProduct(orderProductFound);
            orderProductRepository.deleteById(orderProductId);
        }
        else throw new EntityNotFoundException("OrderProduct with Id: "+ orderProductId + " Not found");
    }
}
