package com.homecleaning.Repository;

import com.homecleaning.Entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
