package com.marting.store.entity;

import com.marting.store.entity.abstractEntities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
public class Client extends User implements Serializable {

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Map<Long, Payment> paymentMethods;

    public Client() {
        paymentMethods = new HashMap<Long, Payment>();
    }

    public Client(String name, String email, String password) {
        super(name, email, password);
        paymentMethods = new HashMap<Long, Payment>();
    }

    public Map<Long, Payment> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMehtod(Long paymentId, Payment newPaymentMehtod) {
        paymentMethods.put(paymentId, newPaymentMehtod);
    }

    public Payment getPaymentMethod(Long paymentId) {
        return paymentMethods.get(paymentId);
    }

    public void setPaymentMethods(Map<Long, Payment> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

}
