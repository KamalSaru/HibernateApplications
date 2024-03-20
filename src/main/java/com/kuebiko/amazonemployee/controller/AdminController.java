package com.kuebiko.amazonemployee.controller;

import com.kuebiko.amazonemployee.dto_entity.AdminLogin;
import com.kuebiko.amazonemployee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("http://localhost:4200/") //for frontend angular
public class AdminController {

    @Autowired

    private AdminService adminService;

    //@PostMapping-----Register admin login details---------
    //In Postman---http://localhost:8080/admin/action/register
    @PostMapping("/admin/action/register")
    public ResponseEntity<String> saveRegisterAdminLogin(@RequestBody AdminLogin adminLogin){
        this.adminService.saveRegisterAdminLogin(adminLogin);
        return ResponseEntity.ok("Admin login details successfully registered.");
    }

    //@PostMapping----Admin login authentication verify--------------
    //In Postman---http://localhost:8080/admin/action/login
    @PostMapping("/admin/action/login")
    public ResponseEntity<String>validateAdmin(@RequestBody AdminLogin adminLogin){
        //Postmapping--@Request only email and password to login---
        this.adminService.findByEmailAndPassword(adminLogin.getEmail(),adminLogin.getPassword());
        return ResponseEntity.ok(adminService.findByEmailAndPassword(adminLogin.getEmail(), adminLogin.getPassword()));
    }
}
