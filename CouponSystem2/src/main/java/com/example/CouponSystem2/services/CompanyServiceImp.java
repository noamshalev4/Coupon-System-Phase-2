package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.exceptions.CouponSystemException;
import com.example.CouponSystem2.security.ClientType;
import com.example.CouponSystem2.security.LoginResponse;
import com.example.CouponSystem2.security.TokenInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImp extends ClientService implements CompanyService {

    @Override
    public LoginResponse login(String email, String password) {
        Company company = companyRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSystemException("email or password is wrong"));

        TokenInformation tokenInformation = TokenInformation.builder()
                .id(company.getId())
                .clientType(ClientType.COMPANY)
                .email(email)
                .build();

        UUID token = tokenManager.addToken(tokenInformation);

        return LoginResponse.builder()
                .token(token)
                .clientType(ClientType.COMPANY)
                .email(email)
                .id(company.getId())
                .name(company.getName())
                .expirationTime(tokenInformation.getExpirationTime())
                .build();
    }

    @Override
    @Transactional
    public Coupon addCoupon(UUID token, Coupon coupon) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);

        if (coupon == null)
            throw new CouponSystemException("Missing coupon details");

        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle().trim(), companyId))
            throw new CouponSystemException("There is already exist coupon with this title at this company");

        if(coupon.getAmount() <= 0)
            throw new CouponSystemException("Amount must be greater than 0");

        if(coupon.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new CouponSystemException("Price must be greater than 0");

        if(coupon.getStartDate().after(coupon.getEndDate()))
            throw new CouponSystemException("Start date must be before end date");

        return couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public Coupon updateCoupon(UUID token, Coupon coupon) {
        if (coupon == null)
            throw new CouponSystemException("Missing coupon details");

        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        Coupon couponFromDb = couponRepository.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException("Coupon does not exist"));

        //if (!couponRepository.existsByIdAndCompanyId(coupon.getId(), companyId))
        //throw new CouponSystemException("Coupon does not exist by this id");

        if (!couponFromDb.getTitle().equalsIgnoreCase(coupon.getTitle())) {
            if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle().trim(), companyId))
                throw new CouponSystemException("There is already exist coupon with this title at this company");
        }

        if(coupon.getAmount() <= 0)
            throw new CouponSystemException("Amount must be greater than 0");

        if(coupon.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new CouponSystemException("Price must be greater than 0");

        if(coupon.getStartDate().after(coupon.getEndDate()))
            throw new CouponSystemException("Start date must be before end date");

        return couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public void deleteCoupon(UUID token, long couponId) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        if (!couponRepository.existsById(couponId))
            throw new CouponSystemException("Coupon does not exist by this id");
        couponRepository.deleteAllCouponsHistoryPurchaseByCouponId(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons(UUID token) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public Coupon getOneCoupon(UUID token, long couponId) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        return couponRepository.findById(couponId).orElseThrow(
                () -> new CouponSystemException("Coupon does not exist by this id"));
    }

    @Override
    public List<Coupon> getAllCouponsByCategory(UUID token, Category category) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        return couponRepository.findByCategoryAndCompanyId(category, companyId);
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(UUID token, BigDecimal maxPrice) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        return couponRepository.findByPriceLessThanEqualAndCompanyId(maxPrice, companyId);
    }

    @Override
    public Company getOneCompany(UUID token) {
        Long companyId = tokenManager.validateToken(token, ClientType.COMPANY);
        return companyRepository.findById(companyId).orElseThrow(
                () -> new CouponSystemException("Company does not exist by this id"));
    }
}
