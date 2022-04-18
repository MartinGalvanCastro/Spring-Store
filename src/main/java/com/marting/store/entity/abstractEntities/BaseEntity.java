package com.marting.store.entity.abstractEntities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
abstract public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object ob){
        if(this==ob){
            return true;
        }
        if(ob == null){
            return false;
        }
        if(getClass()!=ob.getClass()){
            return false;
        }
        BaseEntity other = (BaseEntity) ob;
        return Objects.equals(this.id,other.id);
    }

}
