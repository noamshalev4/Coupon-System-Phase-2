package com.example.CouponSystem2.repositories;

import com.example.CouponSystem2.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, long id);
    boolean existsByName(String name);
    boolean existsByEmailAndPassword(String email, String password);
    Optional<Company> findByEmailAndPassword(String email, String password);
}
