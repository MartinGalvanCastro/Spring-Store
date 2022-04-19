package com.marting.store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marting.store.entity.abstractEntities.BaseEntity;
import com.marting.store.entity.abstractEntities.User;
import com.marting.store.entity.constants.Constant;
import com.marting.store.entity.constants.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Order_Table")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order extends BaseEntity implements Serializable {

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order Status "+ Constant.PROPERTY_NOT_NULL)
    private OrderStatus orderStatus;

    @Column
    @NotNull(message = "Total Price "+Constant.PROPERTY_NOT_NULL)
    @Min(value=1,
            message = "Total Price "+Constant.NUM_PROPERTY_GREATER_THAN+" 1")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"ordersSubmitted","paymentMethods","password"})
    private Client orderOwner;

    public Order() {
        this.orderStatus = OrderStatus.NEW;
        this.totalPrice = 10;
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

    public void setOrderOwner(Client orderOwner) {
        this.orderOwner = orderOwner;
    }
}
