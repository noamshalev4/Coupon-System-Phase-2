package com.example.CouponSystem2.repositories;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // Company-related queries
    boolean existsByTitleAndCompanyId(String title, Long companyId);
    boolean existsByIdAndCompanyId(Long couponId, Long CompanyId);
    List<Coupon> findByCompanyId(Long CompanyId);
    List<Coupon> findByCategoryAndCompanyId(Category category, Long companyId);
    List<Coupon> findByPriceLessThanEqualAndCompanyId(BigDecimal maxPrice, Long companyId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupons WHERE company_id = ?;", nativeQuery = true)
    void deleteAllCouponsByCompany(Long companyId);

    @Transactional
    @Modifying
    @Query(value = "DELETE customers_coupons " +
            "FROM customers_coupons " +
            "JOIN coupons ON customers_coupons.coupon_id = coupons.id " +
            "WHERE coupons.company_id = ?;", nativeQuery = true)
    void deleteAllCouponsHistoryPurchaseByCompany(Long companyId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons " +
            "WHERE coupon_id = ?;", nativeQuery = true)
    void deleteAllCouponsHistoryPurchaseByCouponId(Long couponId);


    // Customer-related queries
    @Query(value = "SELECT CASE WHEN COUNT(customer_id) > 0 THEN true ELSE false END FROM customers_coupons " +
            "WHERE coupon_id = ? AND customer_id = ?", nativeQuery = true)
    long existsByIdAndCustomerId(Long couponId, Long customerId);

    @Query(value = "SELECT coupons.id, coupons.company_id, coupons.category, coupons.title, coupons.description," +
            " coupons.start_date, coupons.end_date, coupons.amount, coupons.price, coupons.image" +
            " FROM coupons JOIN customers_coupons ON coupons.id = " +
            "customers_coupons.coupon_id WHERE customers_coupons.customer_id = ?;", nativeQuery = true)
    List<Coupon> findCouponByCustomerId(Long customerId);

    @Query(value = "SELECT coupons.* " +
            "FROM coupons " +
            "JOIN customers_coupons ON coupons.id = customers_coupons.coupon_id " +
            "WHERE customers_coupons.customer_id = ? AND coupons.category = ?;", nativeQuery = true)
    List<Coupon> findCouponByCategoryAndCustomerId(Category category, Long customerId);

    @Query(value = "SELECT coupons.* " +
            "FROM coupons " +
            "JOIN customers_coupons ON coupons.id = customers_coupons.coupon_id " +
            "WHERE customers_coupons.customer_id = ?2 " +
            "AND coupons.price <= ?1;", nativeQuery = true)
    List<Coupon> findByPriceLessThanEqualAndCustomerId(BigDecimal maxPrice, Long customerId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_coupons VALUES (?, ?)", nativeQuery = true)
    void purchaseCoupon(Long couponId, Long customerId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons " +
            "WHERE customer_id = ?;", nativeQuery = true)
    void deleteAllCouponsHistoryPurchaseByCustomer(Long customerId);

    // Job-related queries
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupons WHERE end_date < CURDATE()", nativeQuery = true)
    void deleteExpiredCoupons();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE coupon_id IN (SELECT id FROM coupons WHERE end_date < CURDATE()) ", nativeQuery = true)
    void deleteExpiredPurchasedCoupons();
}


