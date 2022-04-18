package com.marting.store.entity;

import com.marting.store.entity.abstractEntities.BaseEntity;
import com.marting.store.entity.constants.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class Product extends BaseEntity implements Serializable {

    @Column
    @NotBlank(message = "Name "+ Constant.PROPERTY_NOT_BLANK)
    @NotNull(message = "Name "+Constant.PROPERTY_NOT_NULL)
    private String name;

    @Column
    @NotNull(message = "Price "+Constant.PROPERTY_NOT_NULL)
    @Min(value=1,
        message = "Price "+Constant.NUM_PROPERTY_GREATER_THAN+" 1")
    private double price;

    public Product() {
        super();
    }

    public Product(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
