package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Customer;
import java.util.List;
import java.util.UUID;

public interface AdminService {

    // Company Management.
    Company addCompany(UUID token, Company company);
    Company updateCompany(UUID token, Company company);
    void deleteCompany(UUID token, long companyId);
    List<Company> getAllCompanies(UUID token);
    Company getOneCompany(UUID token, long companyId);

    // Customer Management.
    Customer addCustomer(UUID token, Customer customer);
    Customer updateCustomer(UUID token, Customer customer);
    void deleteCustomer(UUID token, long customerId);
    List<Customer> getAllCustomers(UUID token);
    Customer getOneCustomer(UUID token, long customerId);
}
