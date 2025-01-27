package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.entities.Customer;
import com.example.CouponSystem2.exceptions.CouponSystemException;
import com.example.CouponSystem2.security.ClientType;
import com.example.CouponSystem2.security.LoginResponse;
import com.example.CouponSystem2.security.TokenInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImp extends ClientService implements CustomerService{
    @Override
    public LoginResponse login(String email, String password) {
        Customer customer = customerRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSystemException("email or password is wrong"));

        TokenInformation tokenInformation = TokenInformation.builder()
                .id(customer.getId())
                .clientType(ClientType.CUSTOMER)
                .email(email)
                .build();

        UUID token = tokenManager.addToken(tokenInformation);

        return LoginResponse.builder()
                .token(token)
                .clientType(ClientType.CUSTOMER)
                .email(email)
                .id(customer.getId())
                .name(customer.getFirstName())
                .expirationTime(tokenInformation.getExpirationTime())
                .build();
    }

    @Override
    @Transactional
    public Coupon purchaseCoupon(UUID token, Long couponId) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);

        Coupon couponFromDb = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException("Coupon id does not exists"));

        LocalDate today = LocalDate.now();
        Date sqlToday = Date.valueOf(today);

        if (couponRepository.existsByIdAndCustomerId(couponId, customerId) > 0)
            throw new CouponSystemException("Customer already have this coupon");
        if (couponFromDb.getAmount() <= 0)
            throw new CouponSystemException("No coupons in stock");
        if (couponFromDb.getEndDate().before(sqlToday))
            throw new CouponSystemException("The coupon expired");

        int currentAmount = couponFromDb.getAmount();
        couponFromDb.setAmount(currentAmount-1);

       couponRepository.save(couponFromDb);
       couponRepository.purchaseCoupon(couponId, customerId);
        return couponFromDb;
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(UUID token) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        return couponRepository.findCouponByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getAllCoupons(UUID token) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCouponsByCategory(UUID token, Category category) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        return couponRepository.findCouponByCategoryAndCustomerId(category, customerId);
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(UUID token, BigDecimal maxPrice) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        if (maxPrice.doubleValue() <= 0.0) {
            throw new CouponSystemException("max price can not be zero or below");
        }
        return couponRepository.findByPriceLessThanEqualAndCustomerId(maxPrice, customerId);
    }

    @Override
    public Customer getOneCustomer(UUID token) {
        Long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        return customerRepository.findById(customerId).orElseThrow(
                ()-> new CouponSystemException("Customer not exist by this id"));
    }
}
