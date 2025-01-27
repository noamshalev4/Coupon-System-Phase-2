package com.example.CouponSystem2.services;

import com.example.CouponSystem2.entities.Company;
import com.example.CouponSystem2.entities.Customer;
import com.example.CouponSystem2.exceptions.CouponSystemException;
import com.example.CouponSystem2.security.ClientType;
import com.example.CouponSystem2.security.LoginResponse;
import com.example.CouponSystem2.security.TokenInformation;
import com.example.CouponSystem2.utilities.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImp extends ClientService implements AdminService {

    @Override
    public LoginResponse login(String email, String password) {
        if (!(email.equalsIgnoreCase("admin@admin.com") && password.equals("admin"))) {
            throw new CouponSystemException("email or password is wrong");
        }

        TokenInformation tokenInformation = TokenInformation.builder()
                .id(-1L)
                .clientType(ClientType.ADMIN)
                .email(email)
                .build();

        UUID token = tokenManager.addToken(tokenInformation);

        return LoginResponse.builder()
                .token(token)
                .clientType(ClientType.ADMIN)
                .email(email)
                .id(-1L)
                .name("Admin")
                .expirationTime(tokenInformation.getExpirationTime())
                .build();
    }

    @Override
    @Transactional
    public Company addCompany(UUID token, Company company) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        if (company  == null) {
            throw new CouponSystemException("Missing company details");
        }

        if(!Validator.isValidEmail(company.getEmail())) {
            throw new CouponSystemException("Email is not valid");
        }

        if(!Validator.isValidPassword(company.getPassword())) {
            throw new CouponSystemException("Password is not valid");
        }

        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException("Email already exists");
        }

        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException("Name already exists");
        }
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public Company updateCompany(UUID token, Company company) {
        tokenManager.validateToken(token, ClientType.ADMIN);

        if (company  == null) {
            throw new CouponSystemException("Missing company details");
        }

        Company companyFromDb = companyRepository.findById(company.getId()).orElseThrow(() -> new CouponSystemException("company not found"));

        if(!Validator.isValidEmail(company.getEmail())) {
            throw new CouponSystemException("Email is not valid");
        }

        if(!Validator.isValidPassword(company.getPassword())) {
            throw new CouponSystemException("Password is not valid");
        }

        if (companyRepository.existsByEmailAndIdNot(company.getEmail(), company.getId())) {
            throw new CouponSystemException("Email already exists");
        }

        if (!companyFromDb.getName().equalsIgnoreCase(company.getName())) {
            throw new CouponSystemException("Name not allow to change");
        }

        companyFromDb.setEmail(company.getEmail());
        companyFromDb.setPassword(company.getPassword());

        return companyRepository.saveAndFlush(companyFromDb);
    }

    @Override
    @Transactional
    public void deleteCompany(UUID token, long companyId) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException("Company does not exists");
        }

        couponRepository.deleteAllCouponsHistoryPurchaseByCompany(companyId);
        couponRepository.deleteAllCouponsByCompany(companyId);
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies(UUID token) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(UUID token, long companyId) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        return companyRepository.findById(companyId).orElseThrow(
                () -> new CouponSystemException("Company does not exists"));
    }

    @Override
    @Transactional
    public Customer addCustomer(UUID token, Customer customer) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        if (customer  == null) {
            throw new CouponSystemException("Missing customer details");
        }

        if(!Validator.isValidEmail(customer.getEmail())) {
            throw new CouponSystemException("Email is not valid");
        }

        if(!Validator.isValidPassword(customer.getPassword())) {
            throw new CouponSystemException("Password is not valid");
        }

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException("Email already exists");
        }
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(UUID token, Customer customer) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        if (customer  == null) {
            throw new CouponSystemException("Missing company details");
        }

        if(!Validator.isValidEmail(customer.getEmail())) {
            throw new CouponSystemException("Email is not valid");
        }

        if(!Validator.isValidPassword(customer.getPassword())) {
            throw new CouponSystemException("Password is not valid");
        }

        if (customerRepository.existsByEmailAndIdNot(customer.getEmail(), customer.getId())){
            throw new CouponSystemException("Email already exists");
        }

        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(UUID token, long customerId) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException("Customer does not exists");
        }
        couponRepository.deleteAllCouponsHistoryPurchaseByCustomer(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers(UUID token) {
        tokenManager.validateToken(token, ClientType.ADMIN);
       return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(UUID token, long customerId) {
        tokenManager.validateToken(token, ClientType.ADMIN);
        return customerRepository.findById(customerId).orElseThrow(
                () -> new CouponSystemException("Customer does not exists"));
    }
}
