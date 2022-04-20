package com.marting.store.repository;

import com.marting.store.entity.OrderProduct;
import com.marting.store.entity.embeeded.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
