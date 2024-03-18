package com.kuebiko.amazonemployee.controller;


import com.kuebiko.amazonemployee.dto.EmployeeDTO;
import com.kuebiko.amazonemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController//used to create REST ful web services using Spring MVC, Mapping request data
//@RequestMapping("employee")//used to map web requests onto specific handler classes and/or handler methods
//@CrossOrigin("http://localhost:4200/") //Angular connections from java(backend)

public class EmploeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee/action/insert") //No need @Request Mapping "employee"
    //Posting employee details information--------------
    //In PostMan Postmapping------http://localhost:8080/employee/action/insert
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return "Employee details save successfully.";
    }

    @GetMapping(value = "/employee/action/get-all")
    //Getting all list of employee details------------------
    //Getmapping----http://localhost:8080/employee/action/get-all
    public ResponseEntity<List<EmployeeDTO>>getAllEmployeeDetails(){
        return new ResponseEntity<>(employeeService.getAllEmployeeDetails(),HttpStatus.OK);
    }


    @GetMapping(value = "/employee/action/get-by-id")
    public ResponseEntity<?>getEmployeeByEmpBatchID(@RequestParam Long empBatchID){
        return  new ResponseEntity<>(employeeService.getEmployeeByEmpBatchID(empBatchID), HttpStatus.OK);
    }
}
