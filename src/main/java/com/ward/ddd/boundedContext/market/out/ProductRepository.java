package com.ward.ddd.boundedContext.market.out;

import com.ward.ddd.boundedContext.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
