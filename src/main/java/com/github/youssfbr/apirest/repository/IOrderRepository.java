package com.github.youssfbr.apirest.repository;

import com.github.youssfbr.apirest.entity.OrderHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderHateoas, Long> {
}
