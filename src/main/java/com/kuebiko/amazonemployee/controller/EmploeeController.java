package com.kuebiko.amazonemployee.controller;


import com.kuebiko.amazonemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmploeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/empinfo/action/ge-by-id")
    public ResponseEntity<?>
}
