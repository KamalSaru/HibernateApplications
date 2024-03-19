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
    //Getmapping---find the employee details using empBatchID--@Request param empBatchID
    //http://localhost:8080/employee/action/get-by-id---Request Param --empBatchID-101
    public ResponseEntity<?>getEmployeeByEmpBatchID(@RequestParam Long empBatchID){
        return  new ResponseEntity<>(employeeService.getEmployeeByEmpBatchID(empBatchID), HttpStatus.OK);
    }

    @GetMapping(value = "/employee/action/get-by-id/{empBatchID}")
    //@GetMapping----getting method using empBatchID-------------------
    //In PostMan------http://localhost:8080/employee/action/get-by-id/102
    public ResponseEntity<?>getEmployeeDetailsByEmpBatchID(@PathVariable("empBatchID") Long empBatchID){
        return ResponseEntity.ok(employeeService.getEmployeeDetailsByEmpBatchID(empBatchID));
    }

    @GetMapping(value = "/employee/action/search/{firstName}")
    //@GetMapping-----Search/find employee details using first name-------------
    //In Postman---http://localhost:8080/employee/action/search/kamal
    public ResponseEntity<List<EmployeeDTO>>searchEmployeeByFirstName(@PathVariable("firstName") String firstName){
        return ResponseEntity.ok(employeeService.searchByEmployeeFirstName(firstName));
    }

    @DeleteMapping(value = "/employee/action/delete/{ID}")
    //@DeleteMapping-----Delete customer using ID------------------------
    //http://localhost:8080/employee/action/delete/5
    public ResponseEntity<?>deleteByID(@PathVariable("ID") Long ID) {
        return ResponseEntity.ok(employeeService.deleteEmployeeByID(ID));
    }


    @PutMapping(value = "employee/action/update/{ID}")
    //@Putmapping----get the employee details first using ID and update the details----
    //http://localhost:8080/employee/action/update/9
    public ResponseEntity<String>updateEmployeeDetails(@RequestBody EmployeeDTO employeeDTO, @PathVariable("ID") Long ID){
        return ResponseEntity.ok(employeeService.updateEmployeeDetails(employeeDTO, ID));
    }
}
