package com.marting.store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marting.store.entity.abstractEntities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Client extends User implements Serializable {

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Payment> paymentMethods;

    @OneToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "orderOwner")
    private List<Order> ordersSubmitted;

    public Client() {
        paymentMethods = new ArrayList<>();
        ordersSubmitted = new ArrayList<>();
    }

    public Client(String name, String email, String password) {
        super(name, email, password);
        paymentMethods = new ArrayList<>();
        ordersSubmitted = new ArrayList<>();
    }

    public List<Payment> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public void addPaymentMehtod(Payment newPaymentMehtod) {
        paymentMethods.add(newPaymentMehtod);
    }

    public Optional<Payment> getPaymentMethod(Long paymentId) {
        return paymentMethods.stream().filter(payment -> payment.getId().equals(paymentId)).findFirst();
    }

    public void setPaymentMethods(List<Payment> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Order> getOrdersSubmitted() {
        return ordersSubmitted;
    }

    public void setOrdersSubmitted(List<Order> ordersSubmitted) {
        this.ordersSubmitted = ordersSubmitted;
    }

    public void addOrder(Order orderSubmitted){
        orderSubmitted.setOrderOwner(this);
        ordersSubmitted.add(orderSubmitted);
    }

    public Optional<Order> getOrderSubmitted(Long orderId){
        return  ordersSubmitted.stream().filter(order -> order.getId().equals(orderId)).findFirst();
    }

}
