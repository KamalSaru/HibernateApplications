package com.kuebiko.amazonemployee.service;

import com.kuebiko.amazonemployee.dto.EmployeeDTO;
import com.kuebiko.amazonemployee.model.Employee;
import com.kuebiko.amazonemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //@PostMapping----Post/save all the employee details PostMapping----------
    public void saveEmployee(EmployeeDTO employeeDTO){
        employeeRepository.save(employeeDTO);
    }

    //@GetMapping---Getting all list of employee details------------------
    public List<EmployeeDTO>getAllEmployeeDetails(){
        return employeeRepository.findAll();
    }

    //Getmapping---find the employee details using empBatchID--@Request param empBatchID
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

    //@GetMapping----getting method using empBatchID-------------------
    public EmployeeDTO getEmployeeDetailsByEmpBatchID(Long empBatchID){
        Optional<EmployeeDTO> employeeDTO= Optional.ofNullable((employeeRepository.findByEmpBatchID(empBatchID)));
        if (employeeDTO.isPresent()){
            return employeeDTO.get();
        }else {
            return null;
        }
    }

    //@GetMapping-----Search/find employee details using first name-------------
    public List<EmployeeDTO> searchByEmployeeFirstName(String firstName){
        employeeRepository.findByFirstName(firstName);
        return employeeRepository.findByFirstName(firstName);
    }

    //@DeleteMapping-----Delete employee using ID------------------------
    public boolean deleteEmployeeByID(Long ID) {
        Optional<EmployeeDTO> employeeDTOOptional = employeeRepository.findById(ID);
        if (employeeDTOOptional.isPresent()) {
            employeeRepository.deleteById(ID);
            System.out.println("Employee with ID " + ID + " deleted successfully.");
        } else {
            System.out.println("Employee with ID " + ID +  " not found.");
        }
        return true;
    }

    //@PutMapping-----Edit/Update details using ID--------

}
