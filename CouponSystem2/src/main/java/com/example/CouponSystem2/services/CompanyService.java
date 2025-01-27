package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Coupon;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CompanyService {
    Coupon addCoupon(UUID token, Coupon coupon);
    Coupon updateCoupon(UUID token, Coupon coupon);
    void deleteCoupon(UUID token, long couponId);
    List<Coupon> getAllCoupons(UUID token);
    Coupon getOneCoupon(UUID token, long couponId);
    List<Coupon> getAllCouponsByCategory(UUID token, Category category);
    List<Coupon> getAllCouponsByMaxPrice(UUID token, BigDecimal maxPrice);
    Company getOneCompany(UUID token);
}
