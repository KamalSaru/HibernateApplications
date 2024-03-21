package com.kuebiko.amazonemployee.service;

import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;
import com.kuebiko.amazonemployee.model.Employee;
import com.kuebiko.amazonemployee.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //@PostMapping--Post/save all the employee details PostMapping----------
    public void saveEmployee(EmployeeDTO employeeDTO){
        employeeRepository.save(employeeDTO);
    }

    //@GetMapping--Getting all list of employee details------------------
    public List<EmployeeDTO>getAllEmployeeDetails(){
        return employeeRepository.findAll();
    }

    //@Getmapping--find the employee details using empBatchID--@Request param empBatchID
    public Employee getEmployeeByEmpBatchID(Long empBatchID){
        EmployeeDTO employeeDTO=employeeRepository.findByEmpBatchID(empBatchID);
        Employee employee=new Employee();
        employee.setEmpBatchID(employeeDTO.getEmpBatchID());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDob(employeeDTO.getDob());
        employee.setAge(employeeDTO.getAge());
        employee.setPosition(employeeDTO.getPosition());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setGender(employeeDTO.getGender());
        return employee;
    }

    //@GetMapping--getting method using empBatchID-------------------
    public EmployeeDTO getEmployeeDetailsByEmpBatchID(Long empBatchID){
        Optional<EmployeeDTO> employeeDTO= Optional.ofNullable((employeeRepository.findByEmpBatchID(empBatchID)));
        if (employeeDTO.isPresent()){
            return employeeDTO.get();
        }else {
            return null;
        }
    }

    //@GetMapping--Search/find employee details using first name-------------
    public List<EmployeeDTO> searchByEmployeeFirstName(String firstName){
        employeeRepository.findByFirstName(firstName);
        return employeeRepository.findByFirstName(firstName);
    }

    //@DeleteMapping--Delete employee using ID------------------------
    public String deleteEmployeeByID(Long ID) {
        Optional<EmployeeDTO> employeeDTOOptional = employeeRepository.findById(ID);
        if (employeeDTOOptional.isPresent()) {
            employeeRepository.deleteById(ID);//Delete ny ID
            return ("Employee with ID number " + ID + " deleted successfully.");
        } else {
            return ("Employee with ID number " + ID +  " doesn't exit.");
        }
    }


    //@PutMapping--Edit/Update details using ID--------
    public String updateEmployeeDetails(EmployeeDTO updateEmployeeInfo, Long ID){
        Optional<EmployeeDTO> employeeOptional = employeeRepository.findById(ID);
        if (employeeOptional.isPresent()){
            EmployeeDTO employeeDTO = employeeOptional.get();
            employeeDTO.setEmpBatchID(updateEmployeeInfo.getEmpBatchID());
            employeeDTO.setFirstName(updateEmployeeInfo.getFirstName());
            employeeDTO.setLastName(updateEmployeeInfo.getLastName());
            employeeDTO.setDob(updateEmployeeInfo.getDob());
            //employeeDTO.setAge(updateEmployeeInfo.getAge());
            employeeDTO.setPosition(updateEmployeeInfo.getPosition());
            employeeDTO.setPhoneNumber(updateEmployeeInfo.getPhoneNumber());
            employeeDTO.setEmail(updateEmployeeInfo.getEmail());
            employeeDTO.setAddress(updateEmployeeInfo.getAddress());
            employeeDTO.setGender(updateEmployeeInfo.getGender());

            //Save the details-----
            employeeRepository.save(employeeDTO);
            return "Employee with ID number " + ID + " updated successfully.";
        } else {
            //Handle the case where the order with the given orderId is not found
            throw new RuntimeException("Employee with ID number " + ID + " doesn't exit.");
        }
    }

    //@PatchMapping--Patch method use to update partial field in the column(Replace update data)-------
    public String updateEmpDetailsPartially(@RequestBody EmployeeDTO updateEmployeeDetails, @PathVariable Long ID){
        Optional<EmployeeDTO> employeeDTOOptional=employeeRepository.findById(ID);
        if(employeeDTOOptional.isPresent()){
            //Exist/catch data and update necessary field--------
            EmployeeDTO existingEmployeeData =employeeDTOOptional.get();
            if (updateEmployeeDetails.getEmpBatchID() !=null){
                existingEmployeeData.setEmpBatchID(updateEmployeeDetails.getEmpBatchID());
            }
            if (updateEmployeeDetails.getFirstName() !=null){
                existingEmployeeData.setFirstName(updateEmployeeDetails.getFirstName());
            }
            if (updateEmployeeDetails.getLastName() !=null){
                existingEmployeeData.setLastName(updateEmployeeDetails.getLastName());
            }
            if (updateEmployeeDetails.getDob() !=null){
                existingEmployeeData.setDob(updateEmployeeDetails.getDob());
            }
            if (updateEmployeeDetails.getAge() !=null){
                existingEmployeeData.setAge(updateEmployeeDetails.getAge());
            }
            if (updateEmployeeDetails.getPosition() !=null){
                existingEmployeeData.setPosition(updateEmployeeDetails.getPosition());
            }
            if (updateEmployeeDetails.getPhoneNumber() !=null){
                existingEmployeeData.setPhoneNumber(updateEmployeeDetails.getPhoneNumber());
            }
            if (updateEmployeeDetails.getEmail() !=null){
                existingEmployeeData.setEmail(updateEmployeeDetails.getEmail());
            }
            if (updateEmployeeDetails.getAddress() !=null){
                existingEmployeeData.setAddress(updateEmployeeDetails.getAddress());
            }
            if (updateEmployeeDetails.getGender() !=null){
                existingEmployeeData.setGender(updateEmployeeDetails.getGender());
            }
            //Save the updated employee data------
            employeeRepository.save(existingEmployeeData);
            return ("Employee with ID number " + ID +" fields are successfully updated.");
        } else {
            return "Employee with ID number " + ID + "doesn't exist";
        }
    }

    //@Get-mapping--Pagination------------
    //Pagination-large dataset and we want to present it to the user in smaller chunks.
    //http://localhost:8080/employee/action/list-employee-by-pagination?pageNumber=0&pageSize=3&sortBy=empBatchID&sortEmployee=asc
    public List<Employee> listEmployeeByPageNumber(int pageNumber, int pageSize, String sortBy, String sortEmployee){
        Sort sort;
        if (sortEmployee.equalsIgnoreCase("DESC")){
            sort=Sort.by(sortBy).descending();
        }else {
            sort=Sort.by(sortBy).ascending();
        }

        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sort);
        Page<EmployeeDTO> pageResponse =employeeRepository.findAll(pageRequest);

        List<Employee> employees = new ArrayList<>();
        for (
                EmployeeDTO employeeDTO: pageResponse.getContent()){
            Employee employee=new Employee();
            employee.setEmpBatchID(employeeDTO.getEmpBatchID());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setDob(employeeDTO.getDob());
            employee.setAge(employeeDTO.getAge());
            employee.setPosition(employeeDTO.getPosition());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setEmail(employeeDTO.getEmail());
            employee.setAddress(employeeDTO.getAddress());
            employee.setGender(employeeDTO.getGender());
            //Adding employee list
            employees.add(employee);
        }
        return employees;
    }

    @PostConstruct
    //annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization
    //url from ngRok--https://7c88-71-179-31-165.ngrok-free.app/employee/action/get-all
    public void callAPI(){
        RestTemplate restTemplate=new RestTemplate();
        String response = restTemplate.getForObject("https://7c88-71-179-31-165.ngrok-free.app/employee/action/get-all", String.class);
        System.out.println("response : " +response);
    }
}
