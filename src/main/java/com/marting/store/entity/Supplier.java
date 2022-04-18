package com.marting.store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marting.store.entity.abstractEntities.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Supplier extends User implements Serializable {

    @OneToOne(mappedBy = "supplier")
    private Product productSupplied;

    public Supplier() {
    }

    public Supplier(String name, String email, String password) {
        super(name, email, password);
    }

    public Supplier(Product productSupplied) {
        this.productSupplied = productSupplied;
    }

    public Supplier(String name, String email, String password, Product productSupplied) {
        super(name, email, password);
        this.productSupplied = productSupplied;
    }

    public Product getProductSupplied() {
        return productSupplied;
    }

    public void setProductSupplied(Product productSupplied) {
        this.productSupplied = productSupplied;
    }
}
