package com.marting.store.entity;

import com.marting.store.entity.abstractEntities.BaseEntity;
import com.marting.store.entity.constants.Constant;
import com.marting.store.entity.customValidations.CardNumber;
import com.marting.store.entity.customValidations.ExpDate;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table

public class Payment extends BaseEntity {

    @Column
    @NotNull(message = "Card Number "+ Constant.PROPERTY_NOT_NULL)
    @NotBlank(message = "Card Number "+ Constant.PROPERTY_NOT_BLANK)
    @CardNumber
    private String cardNumber;

    @Column
    @Pattern(regexp = "^[2-9][0-9]{3}-(0[1-9]|1[0-2])$",
            message = "Expiration Date format not valid, It is MM/YYYY")
    @NotNull(message = "Expiration Date "+Constant.PROPERTY_NOT_NULL)
    @NotBlank(message = "Expiration Date "+ Constant.PROPERTY_NOT_BLANK)
    @ExpDate
    private String expDate;

    public Payment() {
    }

    public Payment(String cardNumber, String expDate) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
