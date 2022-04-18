package com.marting.store.entity;

import com.marting.store.entity.abstractEntities.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Supplier extends User implements Serializable {
}
