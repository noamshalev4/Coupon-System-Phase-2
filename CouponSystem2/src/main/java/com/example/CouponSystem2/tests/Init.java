package com.example.CouponSystem2.tests;

import com.example.CouponSystem2.entities.Category;
import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Coupon;
import com.example.CouponSystem2.entities.Customer;
import com.example.CouponSystem2.repositories.CompanyRepository;
import com.example.CouponSystem2.repositories.CouponRepository;
import com.example.CouponSystem2.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class Init implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Override
    // This method runs on application startup
    public void run(String... args) throws Exception {
        addData();
    }

    private void addData() {
        Company company1 = new Company(0L, "Amazon", "Amazon@gmail.com", "1234", null);
        Company company2 = new Company(0L, "Google", "Google@gmail.com", "1235", null);
        Company company3 = new Company(0L, "Pinterest", "Pinterest@gmail.com", "1236", null);
        companyRepository.saveAll(List.of(company1, company2, company3));

        company1.setId(1L);
        company2.setId(2L);
        company3.setId(3L);
        Coupon coupon1 = new Coupon(0L, company1, Category.SPORT, "title1", "description1",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)),
                10, BigDecimal.valueOf( 150.0), "https://via.placeholder.com/250x150");

        Coupon coupon2 = new Coupon(0L, company1, Category.ART, "title2", "description2",
                Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(2)),
                0, BigDecimal.valueOf( 175.0), "https://via.placeholder.com/250x150");

        Coupon coupon3 = new Coupon(0L, company1, Category.MOVIES, "title3", "description3",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)),
                20, BigDecimal.valueOf( 200.0), "https://via.placeholder.com/250x150");

        Coupon coupon4 = new Coupon(0L, company2, Category.RESTAURANTS, "title4", "description4",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)),
                25, BigDecimal.valueOf( 250.0), "https://via.placeholder.com/250x150");

        Coupon coupon5 = new Coupon(0L, company2, Category.FOOD, "title5", "description5",
                Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().minusMonths(1)),
                30, BigDecimal.valueOf( 275.0), "https://via.placeholder.com/250x150");

        Coupon coupon6 = new Coupon(0L, company3, Category.SPA, "title6", "description6",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)),
                35, BigDecimal.valueOf( 300.0), "https://via.placeholder.com/250x150");

        List<Coupon> savedCoupons = couponRepository.saveAll(List.of(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6));

        coupon1.setId(savedCoupons.get(0).getId());
        coupon2.setId(savedCoupons.get(1).getId());
        coupon3.setId(savedCoupons.get(2).getId());
        coupon4.setId(savedCoupons.get(3).getId());
        coupon5.setId(savedCoupons.get(4).getId());
        coupon6.setId(savedCoupons.get(5).getId());

        Customer customer1 = new Customer(0L, "Noam", "Shalev", "noamsbest@gmail.com",
                "1234",List.of(coupon1, coupon4));
        Customer customer2 = new Customer(0L, "Yonatan", "Shalev", "Yoni@gmail.com",
                "1235",List.of(coupon1, coupon3));
        Customer customer3 = new Customer(0L, "Daya", "Shalev", "Daya@gmail.com",
                "1236",List.of(coupon4, coupon3));

        customerRepository.saveAll(List.of(customer1, customer2, customer3));
    }
}
