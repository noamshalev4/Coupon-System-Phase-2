package com.example.CouponSystem2.security;

import com.example.CouponSystem2.exceptions.CouponSystemException;
import com.example.CouponSystem2.services.AdminServiceImp;
import com.example.CouponSystem2.services.CompanyServiceImp;
import com.example.CouponSystem2.services.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {
    private final AdminServiceImp adminService;
    private final CompanyServiceImp companyService;
    private final CustomerServiceImp customerService;

    // Handles login for different client types (Admin, Company, Customer).
    public LoginResponse handleLogin(String email, String password, ClientType clientType){
        LoginResponse loginResponse = null;
        switch (clientType) {
            case ADMIN -> {
                loginResponse = adminService.login(email, password);
            }
            case COMPANY -> {
                loginResponse = companyService.login(email, password);
            }
            case CUSTOMER -> {
                loginResponse = customerService.login(email, password);
            }
            case GUEST -> {
                throw new CouponSystemException("Invalid client type");
            }
        }
        return loginResponse;
    }
}
