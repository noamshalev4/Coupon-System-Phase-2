package com.example.CouponSystem2.controllers;

import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Customer;
import com.example.CouponSystem2.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
@RequiredArgsConstructor

public class AdminController {
    private final AdminService adminService;

    //Company:
    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company) {
        return adminService.addCompany(token, company);
    }

    @PutMapping("/company")
    @ResponseStatus(HttpStatus.OK)
    public Company updateCompany(@RequestHeader("Authorization") UUID token,@RequestBody Company company) {
        return adminService.updateCompany(token, company);
    }

    @DeleteMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization")UUID token,@PathVariable Long companyId) {
        adminService.deleteCompany(token, companyId);
    }

    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies(@RequestHeader("Authorization")UUID token) {
        return adminService.getAllCompanies(token);
    }

    @GetMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getOneCompany(@RequestHeader("Authorization")UUID token,@PathVariable long companyId) {
        return adminService.getOneCompany(token, companyId);
    }

    //Customer:
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestHeader("Authorization")UUID token,@RequestBody Customer customer) {
        return adminService.addCustomer(token, customer);
    }


    @PutMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@RequestHeader("Authorization")UUID token,@RequestBody Customer customer) {
        return adminService.updateCustomer(token, customer);
    }

    @DeleteMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization")UUID token,@PathVariable long customerId) {
        adminService.deleteCompany(token, customerId);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(@RequestHeader("Authorization")UUID token) {
        return adminService.getAllCustomers(token);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getOneCustomer(@RequestHeader("Authorization")UUID token,@PathVariable long customerId) {
       return adminService.getOneCustomer(token, customerId);
    }
}
