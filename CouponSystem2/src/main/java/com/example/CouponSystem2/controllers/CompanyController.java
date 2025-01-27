package com.example.CouponSystem2.controllers;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/company")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    //Coupon:
    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) {
        return companyService.addCoupon(token, coupon);
    }

    @PutMapping("/coupon")
    @ResponseStatus(HttpStatus.OK)
    public Coupon updateCoupon(@RequestHeader("Authorization")UUID token,@RequestBody Coupon coupon) {
        return companyService.updateCoupon(token, coupon);
    }

    @DeleteMapping("/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization")UUID token,@PathVariable long couponId) {
        companyService.deleteCoupon(token, couponId);
    }

    @GetMapping("/coupons")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization")UUID token) {
        return companyService.getAllCoupons(token);
    }

    @GetMapping("/coupon/{couponId}")
    @ResponseStatus(HttpStatus.OK)
    public Coupon getOneCoupon(@RequestHeader("Authorization")UUID token,@PathVariable long couponId) {
        return companyService.getOneCoupon(token, couponId);
    }

    @GetMapping("/coupons/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByCategory(@RequestHeader("Authorization")UUID token,@RequestParam Category category) {
        return companyService.getAllCouponsByCategory(token, category);
    }

    @GetMapping("/coupons/max_price")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByMaxPrice(@RequestHeader("Authorization")UUID token,@RequestParam BigDecimal maxPrice) {
        return companyService.getAllCouponsByMaxPrice(token, maxPrice);
    }

    //Company:
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Company getOneCompany(@RequestHeader("Authorization")UUID token) {
        return companyService.getOneCompany(token);
    }
}
