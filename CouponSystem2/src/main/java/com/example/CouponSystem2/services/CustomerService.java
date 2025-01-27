package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.entities.Customer;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Coupon purchaseCoupon(UUID token, Long couponId);
    List<Coupon> getAllCustomerCoupons(UUID token);
    List<Coupon> getAllCoupons(UUID token);
    List<Coupon> getAllCouponsByCategory(UUID token, Category category);
    List<Coupon> getAllCouponsByMaxPrice(UUID token, BigDecimal maxPrice);
    Customer getOneCustomer(UUID token);
}
