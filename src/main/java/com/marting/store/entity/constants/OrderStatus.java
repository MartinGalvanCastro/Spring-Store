package com.marting.store.entity.constants;

public enum OrderStatus {
    NEW("New"),
    IN_PROCESS("In Process"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private String message;

    OrderStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
