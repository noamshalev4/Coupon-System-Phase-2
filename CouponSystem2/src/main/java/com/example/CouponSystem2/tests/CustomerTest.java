//package com.example.CouponSystem2.tests;
//
//import com.example.CouponSystem2.entities.Category;
//import com.example.CouponSystem2.security.ClientType;
//import com.example.CouponSystem2.security.LoginManager;
//import com.example.CouponSystem2.security.LoginResponse;
//import com.example.CouponSystem2.services.CustomerService;
//import com.example.CouponSystem2.utilities.Color;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//@Component
//@Order(4)
//@RequiredArgsConstructor
//
//public class CustomerTest implements CommandLineRunner {
//    private final CustomerService customerService;
//    private final LoginManager loginManager;
//    private UUID token;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(Color.YELLOW_BOLD + "   ___ _   _ ___ _____ ___  __  __ ___ ___   _____ ___ ___ _____ \n" +
//                "  / __| | | / __|_   _/ _ \\|  \\/  | __| _ \\ |_   _| __/ __|_   _|\n" +
//                " | (__| |_| \\__ \\ | || (_) | |\\/| | _||   /   | | | _|\\__ \\ | |  \n" +
//                "  \\___|\\___/|___/ |_| \\___/|_|  |_|___|_|_\\   |_| |___|___/ |_|  \n" +
//                "                                                                 " + Color.RESET);
//        loginTest();
//        System.out.println();
//        purchaseCouponTest();
//        System.out.println();
//        getAllCouponsTest();
//        System.out.println();
//        getAllCouponsByCategoryTest();
//        System.out.println();
//        getAllCouponsByMaxPriceTest();
//        System.out.println();
//        getOneCustomerTest();
//        System.out.println();
//    }
//    // Method to tests the customer login process
//    public void loginTest() {
//        // Method to test customer login success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Customer Login Success:" + Color.RESET);
//            LoginResponse lg = loginManager.handleLogin("Noamsbest@gmail.com", "1234", ClientType.CUSTOMER);
//            token = lg.getToken();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//        // Method to test failed login due to incorrect password
//        try {
//            System.out.println(Color.RED_BOLD + "Customer Login Failed:" + Color.RESET);
//            loginManager.handleLogin("Noamsbest@gmail.com", "12", ClientType.CUSTOMER);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    // Method to test purchasing coupons
//    public void purchaseCouponTest() {
//        // Attempt to purchase a coupon with a valid ID
//        try {
//            System.out.println(Color.GREEN_BOLD + "Purchase coupon success" + Color.RESET);
//            customerService.purchaseCoupon(token, 3L);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Attempt to purchase a coupon that has already been purchased
//        try {
//            System.out.println(Color.RED_BOLD + "Purchase coupon fail (already purchase)" + Color.RESET);
//            customerService.purchaseCoupon(token, 1L);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println();
//        }
//
//        // Attempt to purchase a coupon with an amount of 0
//        try {
//            System.out.println(Color.RED_BOLD + "Purchase coupon fail (amount 0)" + Color.RESET);
//            customerService.purchaseCoupon(token, 4L);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println();
//        }
//
//        // Attempt to purchase an expired coupon
//        try {
//            System.out.println(Color.RED_BOLD + "Purchase coupon fail (expired coupon)" + Color.RESET);
//            customerService.purchaseCoupon(token, 5L);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching all coupons
//    public void getAllCouponsTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons success" + Color.RESET);
//            System.out.println(customerService.getAllCoupons(token));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching coupons by category
//    public void getAllCouponsByCategoryTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons by category success" + Color.RESET);
//            System.out.println(customerService.getAllCouponsByCategory(token, Category.MOVIES));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching coupons by maximum price
//    public void getAllCouponsByMaxPriceTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all coupons by max price success" + Color.RESET);
//            System.out.println(customerService.getAllCouponsByMaxPrice(token, BigDecimal.valueOf( 200.0)));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Method to test fetching the authenticated customer details
//    public void getOneCustomerTest() {
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get one customer success" + Color.RESET);
//            System.out.println(customerService.getOneCustomer(token));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}
