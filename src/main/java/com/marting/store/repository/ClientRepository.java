package com.marting.store.repository;

import com.marting.store.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ClientRepository extends  JpaRepository<Client,Long>{
}
