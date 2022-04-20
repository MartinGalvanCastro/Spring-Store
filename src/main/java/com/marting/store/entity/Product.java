package com.marting.store.entity;

import com.fasterxml.jackson.annotation.*;
import com.marting.store.entity.abstractEntities.BaseEntity;
import com.marting.store.entity.constants.Constant;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                  property = "id")
public class Product extends BaseEntity implements Serializable {

    @Column
    @NotBlank(message = "Name "+ Constant.PROPERTY_NOT_BLANK)
    @NotNull(message = "Name "+Constant.PROPERTY_NOT_NULL)
    private String name;

    @Column
    @NotNull(message = "individualPrice "+Constant.PROPERTY_NOT_NULL)
    @Min(value=1,
        message = "individualPrice "+Constant.NUM_PROPERTY_GREATER_THAN+" 1")
    private double individualPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supplier_id")
    @JsonIgnoreProperties({"password"})
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> orderProductList;

    public Product() {
        super();
    }

    public Product(String name, double price) {
        super();
        this.name = name;
        this.individualPrice = price;
        this.supplier = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIndividualPrice() {
        return individualPrice;
    }

    public void setIndividualPrice(double individualPrice) {
        this.individualPrice = individualPrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<OrderProduct> getOrderProductList() {
        return Collections.unmodifiableList(orderProductList);
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        orderProductList.add(orderProduct);
    }

    public void removeOrderProduct(OrderProduct orderProduct){
        orderProductList.remove(orderProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", individualPrice=" + individualPrice +
                ", supplier=" + supplier +
                ", orderProductList=" + orderProductList +
                '}';
    }
}
