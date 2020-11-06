package com.github.novikovmn.spring1.repo;

import com.github.novikovmn.spring1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> testSelect();

    @Query("select p from Product p")
    public List<Product> testSelect2();
}
