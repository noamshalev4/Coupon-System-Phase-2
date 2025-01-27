package com.example.CouponSystem2.controllers;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.entities.Customer;
import com.example.CouponSystem2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    //Coupon:
    @PostMapping("/purchase_coupon/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable Long couponId) {
        return customerService.purchaseCoupon(token, couponId);
    }

    @GetMapping("/coupons")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCustomerCoupons(@RequestHeader("Authorization") UUID token) {
        return customerService.getAllCustomerCoupons(token);
    }

    @GetMapping("/all-coupons")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) {
        return customerService.getAllCoupons(token);
    }

    @GetMapping("/coupons/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByCategory(@RequestHeader("Authorization") UUID token, @RequestParam Category category) {
        return customerService.getAllCouponsByCategory(token, category);
    }

    @GetMapping("/coupons/max_Price")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByMaxPrice(@RequestHeader("Authorization") UUID token, @RequestParam BigDecimal maxPrice) {
        return customerService.getAllCouponsByMaxPrice(token, maxPrice);
    }

    //Customer:
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Customer getOneCustomer(@RequestHeader("Authorization") UUID token) {
        return customerService.getOneCustomer(token);
    }
}
