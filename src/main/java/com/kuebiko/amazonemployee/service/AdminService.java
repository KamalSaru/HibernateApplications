package com.kuebiko.amazonemployee.service;

import com.kuebiko.amazonemployee.dto_entity.AdminLogin;
import com.kuebiko.amazonemployee.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired

    private AdminRepository adminRepository;

    //@PostMapping-----Register admin login details----------
    public void saveRegisterAdminLogin(AdminLogin adminLogin){adminRepository.save(adminLogin);}


    //@PostMapping----Admin login authentication verify--------------
    public String findByEmailAndPassword(String email, String password){
        Optional<AdminLogin> administration=this.adminRepository.findByEmailAndPassword(email,password);
        if (administration.isPresent()){
            return "Admin login successfully.";
        } else {
            return "Invalid email or password.";
        }
    }
}
