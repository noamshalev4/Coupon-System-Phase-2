//package com.example.CouponSystem2.tests;
//
//import com.example.CouponSystem2.entities.Company;
//import com.example.CouponSystem2.entities.Customer;
//import com.example.CouponSystem2.security.ClientType;
//import com.example.CouponSystem2.security.LoginManager;
//import com.example.CouponSystem2.security.LoginResponse;
//import com.example.CouponSystem2.services.AdminService;
//import com.example.CouponSystem2.utilities.Color;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.ansi.AnsiColor;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Order(2)
//@RequiredArgsConstructor
//public class AdminTest implements CommandLineRunner {
//
//    private final AdminService adminService;
//    private final LoginManager loginManager;
//    private UUID token;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(Color.BLUE_BOLD + "    _   ___  __  __ ___ _  _   _____ ___ ___ _____ \n" +
//                "   /_\\ |   \\|  \\/  |_ _| \\| | |_   _| __/ __|_   _|\n" +
//                "  / _ \\| |) | |\\/| || || .` |   | | | _|\\__ \\ | |  \n" +
//                " /_/ \\_\\___/|_|  |_|___|_|\\_|   |_| |___|___/ |_|  \n" +
//                "                                                   " + Color.RESET);
//        loginTest();
//        System.out.println();
//        addCompanyTest();
//        System.out.println();
//        updateCompanyTest();
//        System.out.println();
//        deleteCompanyTest();
//        System.out.println();
//        getAllCompaniesTest();
//        System.out.println();
//        getOneCompanyTest();
//        System.out.println();
//        addCustomerTest();
//        System.out.println();
//        updateCustomerTest();
//        System.out.println();
//        deleteCustomerTest();
//        System.out.println();
//        getAllCustomersTest();
//        System.out.println();
//        getOneCustomerTest();
//        System.out.println();
//    }
//
//    // Method to tests the admin login process
//    public void loginTest() {
//        // Method to test admin login success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Admin Login Success:" + Color.RESET);
//            LoginResponse lg = loginManager.handleLogin("Admin@admin.com", "admin", ClientType.ADMIN);
//            token = lg.getToken();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test failed login due to incorrect password
//        try {
//            System.out.println(Color.RED_BOLD + "Admin Login Failed:" + Color.RESET);
//            loginManager.handleLogin("Admin@admin.com", "admin123", ClientType.ADMIN);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Company-related tests
//    public void addCompanyTest() {
//        Company companyToAdd = new Company(0L, "Starbucks", "Starbucks@gmail.com", "1234", null);
//        // Method to test adding a company success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Add company Success:" + Color.RESET);
//            adminService.addCompany(token, companyToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test adding the same company again (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Add company Failed (email already exists):" + Color.RESET);
//            adminService.addCompany(token, companyToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding company with a different email (should fail due to duplicate name)
//        try {
//            System.out.println(Color.RED_BOLD + "Add company Failed (name already exists):" + Color.RESET);
//            companyToAdd.setEmail("moshe@gmail.com");
//            adminService.addCompany(token, companyToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding company with invalid email format
//        try {
//            System.out.println(Color.RED_BOLD + "Add company Failed (email not valid):" + Color.RESET);
//            companyToAdd.setEmail("dff2f23");
//            adminService.addCompany(token, companyToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding company with invalid password
//        try {
//            System.out.println(Color.RED_BOLD + "Add company Failed (password not valid):" + Color.RESET);
//            companyToAdd.setEmail("moshe@gmail.com");
//            companyToAdd.setPassword("1");
//            adminService.addCompany(token, companyToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding a null company
//        try {
//            System.out.println(Color.RED_BOLD + "Add company Failed (null):" + Color.RESET);
//            adminService.addCompany(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void updateCompanyTest() {
//        Company companyToUpdate = adminService.getOneCompany(token, 1);
//        companyToUpdate.setEmail("amazon2@gmail.com");
//        companyToUpdate.setPassword("123456789");
//
//        // Method to test updating the company success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Update company Success:" + Color.RESET);
//            adminService.updateCompany(token, companyToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test updating with an email that already exists (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update company Failed (email already exists):" + Color.RESET);
//            companyToUpdate.setEmail("Google@gmail.com");
//            adminService.updateCompany(token, companyToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test attempting to change the company name (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update company Failed (name not allow to change):" + Color.RESET);
//            companyToUpdate.setEmail("Amazon@gmail.com");
//            companyToUpdate.setName("lalala");
//            adminService.updateCompany(token, companyToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with invalid email format
//        try {
//            System.out.println(Color.RED_BOLD + "Update company Failed (email not valid):" + Color.RESET);
//            companyToUpdate.setEmail("dff2f23");
//            adminService.updateCompany(token, companyToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with invalid password
//        try {
//            System.out.println(Color.RED_BOLD + "Update company Failed (password not valid):" + Color.RESET);
//            companyToUpdate.setEmail("Amazon@gmail.com");
//            companyToUpdate.setPassword("1");
//            adminService.updateCompany(token, companyToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with null company
//        try {
//            System.out.println(Color.RED_BOLD + "Update company Failed (null):" + Color.RESET);
//            adminService.updateCompany(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteCompanyTest() {
//        // Method to test success company deletion
//        try {
//            System.out.println(Color.GREEN_BOLD + "Company delete success" + Color.RESET);
//            adminService.deleteCompany(token, 3);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test deletion of a non-existing company (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Company delete fail (company not exist)" + Color.RESET);
//            adminService.deleteCompany(token, 100);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void getAllCompaniesTest() {
//        // Method to test fetching all companies
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all companies success" + Color.RESET);
//            System.out.println(adminService.getAllCompanies(token));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void getOneCompanyTest() {
//        // Method to test fetching a single company by ID
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get one company success" + Color.RESET);
//            System.out.println(adminService.getOneCompany(token, 1));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Customer-related tests
//    public void addCustomerTest() {
//        Customer customerToAdd = new Customer(0L, "Shaked", "BenRatzon", "Shaked@gmail.com", "1234", null);
//        // Method to test adding the customer success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Add customer Success:" + Color.RESET);
//            adminService.addCustomer(token, customerToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test adding the same customer again (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Add customer Failed (email already exists):" + Color.RESET);
//            adminService.addCustomer(token, customerToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding customer with invalid email format
//        try {
//            System.out.println(Color.RED_BOLD + "Add customer Failed (email not valid):" + Color.RESET);
//            customerToAdd.setEmail("dff2f23");
//            adminService.addCustomer(token, customerToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding customer with invalid password (weak password)
//        try {
//            System.out.println(Color.RED_BOLD + "Add customer Failed (password not valid):" + Color.RESET);
//            customerToAdd.setEmail("moshe2@gmail.com");
//            customerToAdd.setPassword("1");
//            adminService.addCustomer(token, customerToAdd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test adding a null customer (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Add customer Failed (null):" + Color.RESET);
//            adminService.addCustomer(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void updateCustomerTest() {
//        Customer customerToUpdate = adminService.getOneCustomer(token, 4);
//        // Method to test updating the customer success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Update customer Success:" + Color.RESET);
//            adminService.updateCustomer(token, customerToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test updating with an email that already exists (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update customer Failed (email already exists):" + Color.RESET);
//            customerToUpdate.setEmail("noamsbest@gmail.com");
//            adminService.updateCustomer(token, customerToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with invalid email format (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update customer Failed (email not valid):" + Color.RESET);
//            customerToUpdate.setEmail("dff2f23");
//            adminService.updateCustomer(token, customerToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with invalid password (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update customer Failed (password not valid):" + Color.RESET);
//            customerToUpdate.setEmail("moshe2@gmail.com");
//            customerToUpdate.setPassword("1");
//            adminService.updateCustomer(token, customerToUpdate);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();
//
//        // Method to test updating with null customer (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Update customer Failed (null):" + Color.RESET);
//            adminService.updateCustomer(token, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteCustomerTest() {
//        // Method to test deleting the customer success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Delete customer success" + Color.RESET);
//            adminService.deleteCustomer(token, 4);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Method to test deletion of a non-existing customer (should fail)
//        try {
//            System.out.println(Color.RED_BOLD + "Delete customer fail (customer not exist)" + Color.RESET);
//            adminService.deleteCustomer(token, 400);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void getAllCustomersTest() {
//        // Method to test fetching all customers success
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get all customers success" + Color.RESET);
//            System.out.println(adminService.getAllCustomers(token));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void getOneCustomerTest() {
//        // Method to test fetching a single customer by ID
//        try {
//            System.out.println(Color.GREEN_BOLD + "Get one customer success" + Color.RESET);
//            System.out.println(adminService.getOneCustomer(token, 1));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}
