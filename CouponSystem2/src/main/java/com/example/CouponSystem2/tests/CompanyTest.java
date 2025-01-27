//package com.example.CouponSystem2.tests;
//
//import com.example.CouponSystem2.entities.Category;
//import com.example.CouponSystem2.entities.Coupon;
//import com.example.CouponSystem2.security.ClientType;
//import com.example.CouponSystem2.security.LoginManager;
//import com.example.CouponSystem2.security.LoginResponse;
//import com.example.CouponSystem2.services.CompanyService;
//import com.example.CouponSystem2.utilities.Color;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Component
//@Order(3)
//@RequiredArgsConstructor
//public class CompanyTest implements CommandLineRunner {
//
//    private final CompanyService companyService;
//    private final LoginManager loginManager;
//    private UUID token;
//
//    public void run(String... args) throws Exception {
//        System.out.println(Color.MAGENTA_BOLD + "   ___ ___  __  __ ___  _   _  ___   __  _____ ___ ___ _____ \n" +
//                "  / __/ _ \\|  \\/  | _ \\/_\\ | \\| \\ \\ / / |_   _| __/ __|_   _|\n" +
//                " | (_| (_) | |\\/| |  _/ _ \\| .` |\\ V /    | | | _|\\__ \\ | |  \n" +
//                "  \\___\\___/|_|  |_|_|/_/ \\_\\_|\\_| |_|     |_| |___|___/ |_|  \n" +
//                "                                                             " + Color.RESET);
//        loginTest();
//        System.out.println();
//        addCouponTest();
//        System.out.println();
//        updateCouponTest();
//        System.out.println();
//        deleteCouponTest();
//        System.out.println();
//        getAllCouponsTest();
//        System.out.println();
//        getOneCouponsTest();
//        System.out.println();
//        getAllCouponsByCategoryTest();
//        System.out.println();
//        getAllCouponsByMaxPriceTest();
//        System.out.println();
//        getOneCompanyTest();
//        System.out.println();
//    }
//
//    // Method to tests the company login process
//    public void loginTest() {
//        // Method to test company login success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Company Login Success:" + Color.RESET);
//            LoginResponse lg = loginManager.handleLogin("Amazon2@gmail.com", "123456789", ClientType.COMPANY);
//            token = lg.getToken();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test failed login due to incorrect password
//        try {
//            System.out.println(Color.RED_BOLD + "Company Login Failed:" + Color.RESET);
//            loginManager.handleLogin("Amazon2@gmail.com", "12", ClientType.COMPANY);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test adding coupons
//    public void addCouponTest() {
//        Coupon couponToAdd = new Coupon(0L, companyService.getOneCompany(token), Category.FOOD, "title100", "description100",
//                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)),
//                200, BigDecimal.valueOf( 250.0), "image100");
//
//        Coupon couponToAdd2 = new Coupon(0L, companyService.getOneCompany(token), Category.MOVIES, "title101", "description101",
//                Date.valueOf(LocalDate.now().minusMonths(4)), Date.valueOf(LocalDate.now().minusMonths(1)),
//                200, BigDecimal.valueOf( 250.0), "image101");
//
//        // Attempt to add the first coupon
//        try {
//            System.out.println(Color.GREEN_BOLD + "Add coupon Success:" + Color.RESET);
//            companyService.addCoupon(token, couponToAdd);
//            companyService.addCoupon(token, couponToAdd2);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Attempt to add a coupon with a duplicate title
//        try {
//            System.out.println(Color.RED_BOLD + "Add coupon Failed (title already exists):" + Color.RESET);
//            couponToAdd.setTitle("title3");
//            companyService.addCoupon(token, couponToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Attempt to add a null coupon
//        try {
//            System.out.println(Color.RED_BOLD + "Add coupon Failed (null):" + Color.RESET);
//            companyService.addCoupon(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//    // Method to test updating coupons
//    public void updateCouponTest() {
//        Coupon couponToUpdate = companyService.getOneCoupon(token, 4);
//        try {
//            System.out.println(Color.GREEN_BOLD + "Update coupon Success:" + Color.RESET);
//            couponToUpdate.setPrice(BigDecimal.valueOf(99));
//            couponToUpdate.setAmount(0);
//
//            companyService.updateCoupon(token, couponToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Attempt to update coupon with a duplicate title
//        try {
//            System.out.println(Color.RED_BOLD + "Update coupon Failed (title already exists):" + Color.RESET);
//            couponToUpdate.setTitle("title2");
//            companyService.updateCoupon(token, couponToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Attempt to update a null coupon
//        try {
//            System.out.println(Color.RED_BOLD + "Update coupon Failed (null):" + Color.RESET);
//            companyService.updateCoupon(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test deleting coupons
//    public void deleteCouponTest() {
//        // Attempt to delete a coupon with a valid ID
//        try {
//            System.out.println(Color.GREEN_BOLD + "Coupon delete success" + Color.RESET);
//            companyService.deleteCoupon(token, 2);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        // Attempt to delete a coupon with an invalid ID
//        try {
//            System.out.println(Color.RED_BOLD + "Coupon delete fail (Coupon not exist)" + Color.RESET);
//            companyService.deleteCoupon(token, 100);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching all coupons
//    public void getAllCouponsTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons success" + Color.RESET);
//            System.out.println(companyService.getAllCoupons(token));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching a single coupon
//    public void getOneCouponsTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get one coupons success" + Color.RESET);
//            System.out.println(companyService.getOneCoupon(token, 1));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching coupons by category
//    public void getAllCouponsByCategoryTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons by category success" + Color.RESET);
//            System.out.println(companyService.getAllCouponsByCategory(token, Category.FOOD));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching coupons by maximum price
//    public void getAllCouponsByMaxPriceTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons by max price success" + Color.RESET);
//            System.out.println(companyService.getAllCouponsByMaxPrice(token, BigDecimal.valueOf( 150.0)));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching the authenticated company details
//    public void getOneCompanyTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get one company success" + Color.RESET);
//            System.out.println(companyService.getOneCompany(token));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}
//
