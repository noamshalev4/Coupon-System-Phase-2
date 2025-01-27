package com.example.CouponSystem2.jobs;

import com.example.CouponSystem2.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveExpiredCouponJob {
    private final CouponRepository couponRepository;


    // Scheduled method to run daily; removes expired coupons
    @Scheduled(fixedRate = (1000 * 60 * 60 * 24), initialDelay = 1000 * 60)
    public void removeExpiredCoupons() {
        couponRepository.deleteExpiredPurchasedCoupons();
        couponRepository.deleteExpiredCoupons();
    }
}
