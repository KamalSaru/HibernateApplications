package com.kuebiko.amazonemployee.controller;

import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;
import com.kuebiko.amazonemployee.model_Class.Employee;
import com.kuebiko.amazonemployee.model_Class.PdfGeneratorUtil;
import com.kuebiko.amazonemployee.model_Class.PdgGeneratorUtilByID;
import com.kuebiko.amazonemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//used to create REST ful web services using Spring MVC, Mapping request data
//@RequestMapping("employee")//used to map web requests onto specific handler classes and/or handler methods
//@CrossOrigin("http://localhost:4200/") //Angular connections from java(backend)

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee/action/insert") //No need @Request Mapping "employee"
    //Posting employee details information--------------
    //In PostMan Postmapping------http://localhost:8080/employee/action/insert
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            employeeService.saveEmployee(employeeDTO);
            return ResponseEntity.ok("Employee details saved successfully.");
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Failed to save employee detail: " +e.getMessage());
        }
    }

    //@GetMapping--getting method using ID-------------------
    //Getmapping--http://localhost:8080/employee/action/find-by-id/15
    @GetMapping(value = "/employee/action/find-by-id/{ID}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable("ID") Long ID){
        try {
            EmployeeDTO employee =employeeService.getEmployeeByID(ID);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/employee/action/get-all")
    //Getting all list of employee details------------------
    //Getmapping----http://localhost:8080/employee/action/get-all
    public ResponseEntity<List<EmployeeDTO>>getAllEmployeeDetails(){
        return new ResponseEntity<>(employeeService.getAllEmployeeDetails(),HttpStatus.OK);
    }


    @GetMapping(value = "/employee/action/get-by-id")
    //Getmapping--find the employee details using empBatchID--@Request param empBatchID
    //http://localhost:8080/employee/action/get-by-id---Request Param --empBatchID-101
    public ResponseEntity<?>getEmployeeByEmpBatchID(@RequestParam Long empBatchID){
        return  new ResponseEntity<>(employeeService.getEmployeeByEmpBatchID(empBatchID), HttpStatus.OK);
    }

    @GetMapping(value = "/employee/action/get-by-id/{empBatchID}")
    //@GetMapping--getting method using empBatchID-------------------
    //In PostMan--http://localhost:8080/employee/action/get-by-id/102
    public ResponseEntity<?>getEmployeeDetailsByEmpBatchID(@PathVariable("empBatchID") Long empBatchID){
        return ResponseEntity.ok(employeeService.getEmployeeDetailsByEmpBatchID(empBatchID));
    }

    @GetMapping(value = "/employee/action/search/{firstName}")
    //@GetMapping--Search/find employee details using first name-------------
    //In Postman--http://localhost:8080/employee/action/search/kamal
    public ResponseEntity<List<EmployeeDTO>>searchEmployeeByFirstName(@PathVariable("firstName") String firstName){
        return ResponseEntity.ok(employeeService.searchByEmployeeFirstName(firstName));
    }

    @DeleteMapping(value = "/employee/action/delete/{ID}")
    //@DeleteMapping--Delete customer using ID------------------------
    //http://localhost:8080/employee/action/delete/5
    public ResponseEntity<?>deleteByID(@PathVariable("ID") Long ID) {
        return ResponseEntity.ok(employeeService.deleteEmployeeByID(ID));
    }


    @PutMapping(value = "/employee/action/update/{ID}")
    //@Putmapping--get the employee details first using ID and update the details----
    //http://localhost:8080/employee/action/update/9
    public ResponseEntity<String>updateEmployeeDetails(@RequestBody EmployeeDTO employeeDTO, @PathVariable("ID") Long ID){
        return ResponseEntity.ok(employeeService.updateEmployeeDetails(employeeDTO, ID));
    }


    //@PatchMapping--Patch method use to update partial field in the column(Replace update data)-------
    //First get Details using ID, CopyPaste, update necessary field and Patch the data--------
    //http://localhost:8080/employee/action/update/field/7
    @PatchMapping(value = "/employee/action/update/field/{ID}")
    public ResponseEntity<String>updateEmpDetailsPartially(@RequestBody EmployeeDTO employeeDTO, @PathVariable("ID") Long ID){
        return ResponseEntity.ok(employeeService.updateEmpDetailsPartiallyField(employeeDTO,ID));
    }


    //Pagination-large dataset and we want to present it to the user in smaller parts.
    //Pagination Ex.-sortBy-Date,Name,Alphabet,Ascending,Descending etc
    //@Getmapping--http://localhost:8080/employee/action/list-employee-by-pagination
    @GetMapping(value = "/employee/action/list-employee-by-pagination")
    //http://localhost:8080/employee/action/list-employee-by-pagination?pageNumber=0&pageSize=3&sortBy=empBatchID&sortEmployee=asc
    public ResponseEntity<?>listEmployeeByPagination(@RequestParam Integer pageNumber, @RequestParam Integer pageSize,
                                                     @RequestParam String sortBy, @RequestParam String sortEmployee){
        List<Employee> employeeList =employeeService.listEmployeeByPageNumber(pageNumber, pageSize, sortBy,sortEmployee);
        ResponseEntity responseEntity=new ResponseEntity<>(employeeList, HttpStatus.OK);
        return responseEntity;
    }


    //Convert to the PDF note_Files
    @GetMapping(value = "/employee/action/convert-pdf-file")
    //@Getmapping--http://localhost:8080/employee/action/convert-pdf-file
    //If you put this url in Browser you will get pdf file-----employee_details.pdf
    public ResponseEntity<byte[]> downloadActivitiesReport() {
        // Fetch list of employee data from the service
        List<EmployeeDTO> employees = employeeService.getAllEmployeeDetails();

        // Check if there are any employees
        if (employees.isEmpty()) {
            // Return 204 No Content if there are no employees
            return ResponseEntity.noContent().build();
        }

        byte[] contents;
        try {
            contents = PdfGeneratorUtil.generateEmployeeDetailsPDF(employees);
        } catch (Exception e) {
            // Logging the error can help in debugging later
            e.printStackTrace();
            // Consider using a logger like SLF4J in real-world applications
            // Logging the error can help in debugging
            // Return 500 Internal Server Error if an exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while generating the PDF.".getBytes());
        }

        // Set response headers for the PDF file download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "employee_details.pdf";
        headers.setContentDispositionFormData("attachment", filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    //Getmapping---get employee details in pdf note_Files using ID
    //Getmapping---http://localhost:8080/employee/action/convert-pdf-file/7
    @GetMapping(value = "/employee/action/convert-pdf-file/{ID}")
    public ResponseEntity<byte[]> downloadPDFEmployeeDetailsByID(@PathVariable("ID") Long ID) {
        // Fetch employee data using ID from the service
        EmployeeDTO employee1= employeeService.findEmployeeByID(ID);
        if (employee1==null) {
            return ResponseEntity.noContent().build();
        }

        byte[] content;
        try {
            content = PdgGeneratorUtilByID.generateEmployeeDetailsPDFByID(employee1);
        } catch (Exception e) { // Catch specific exception type if available
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while generating the PDF.".getBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "employee_details.pdf";
        headers.setContentDispositionFormData("attachment", filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
