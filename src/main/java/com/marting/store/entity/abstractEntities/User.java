package com.marting.store.entity.abstractEntities;

import com.marting.store.entity.constants.Constant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@MappedSuperclass
abstract public class User extends BaseEntity {

    @Column
    @Pattern(regexp = "^[A-Za-z\\s]{4,}$",
            message = "Name " + Constant.PROPERTY_NOT_FOLLOWING_PATTERN + "Only words separated with whitespace")
    @NotNull(message ="Name " +Constant.PROPERTY_NOT_NULL)
    @NotBlank(message = "Name " +Constant.PROPERTY_NOT_BLANK)
    private String name;

    @Column
    @NotNull(message = "Email "+Constant.PROPERTY_NOT_NULL)
    @Email(message = Constant.EMAIL_NOT_VALID)
    private String email;

    @Column
    @NotNull(message = "Password "+Constant.PROPERTY_NOT_NULL)
    @NotBlank(message = "Passwrod "+Constant.PROPERTY_NOT_BLANK)
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }


}
