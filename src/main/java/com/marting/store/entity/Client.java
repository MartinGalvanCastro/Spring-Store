package com.marting.store.entity;

import com.marting.store.entity.abstractEntities.User;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
public class Client extends User implements Serializable {


}
