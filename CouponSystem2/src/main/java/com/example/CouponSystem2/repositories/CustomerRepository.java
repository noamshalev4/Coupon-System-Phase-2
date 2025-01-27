package com.example.CouponSystem2.repositories;

import com.example.CouponSystem2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, long id);
    boolean existsByEmailAndPassword(String email, String password);
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
