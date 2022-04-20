package com.marting.store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marting.store.entity.abstractEntities.BaseEntity;
import com.marting.store.entity.abstractEntities.User;
import com.marting.store.entity.constants.Constant;
import com.marting.store.entity.constants.OrderStatus;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Order_Table")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order extends BaseEntity implements Serializable {

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"ordersSubmitted","paymentMethods","password"})
    private Client orderOwner;


    @OneToMany(mappedBy = "order",
                cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonIgnoreProperties({"order"})
    private List<OrderProduct> orderProductList;

    public Order() {
        this.orderStatus = OrderStatus.NEW;
        this.totalPrice = 0;
        orderProductList = new ArrayList<>();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(@Validated Client orderOwner) {
        this.orderOwner = orderOwner;
    }

    public List<OrderProduct> getOrderProductList() {
        return Collections.unmodifiableList(orderProductList);
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        orderProductList.add(orderProduct);
        totalPrice+=orderProduct.getPrice();
    }

    public void removeOrderProduct(OrderProduct orderProduct){
        orderProductList.remove(orderProduct);
        totalPrice-=orderProduct.getPrice();
    }

    public void setInProgressStatus(){
        orderStatus = OrderStatus.IN_PROCESS;
    }

    public void setShippingStatus(){
        orderStatus = OrderStatus.SHIPPED;
    }

    public void setDeliveredStatus(){
        orderStatus = OrderStatus.SHIPPED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", orderOwner=" + orderOwner +
                ", orderProductList=" + orderProductList +
                '}';
    }
}
