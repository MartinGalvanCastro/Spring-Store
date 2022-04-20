package com.marting.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marting.store.entity.constants.Constant;
import com.marting.store.entity.embeeded.OrderProductId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class OrderProduct implements Serializable {

    @EmbeddedId
    private OrderProductId orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"orderProductList"})
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"supplier","orderProductList"})
    @MapsId("productId")
    private Product product;


    @Column
    @NotNull
    @Min(value = 1, message = "Quantity " + Constant.NUM_PROPERTY_GREATER_THAN + "0")
    private double quantity;

    @Column
    private double price;

    public OrderProduct() {
    }

    public OrderProduct(double quantity) {
        this.quantity = quantity;
    }

    /**
     *  @param order
     * @param product
     * @param quantity
     */
    public OrderProduct(Order order, Product product, double quantity) {
        if(order!=null && product!=null) {
            this.orderProductId = new OrderProductId(order.getId(), product.getId());
            this.order = order;
            this.product = product;
            this.price = quantity* product.getIndividualPrice();
        }
        else{
            this.orderProductId = new OrderProductId();
        }
        this.quantity = quantity;
        updatePrice();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void updatePrice(){
        price = quantity*product.getIndividualPrice();
    }
}
