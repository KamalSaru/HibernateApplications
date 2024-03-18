package com.kuebiko.amazonemployee.service;

import com.kuebiko.amazonemployee.dto.EmployeeDTO;
import com.kuebiko.amazonemployee.model.Employee;
import com.kuebiko.amazonemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Employee getEmployeeByEmpBatchID(Long empBatchID){
        EmployeeDTO employeeDTO=employeeRepository.findByEmpBatchID(empBatchID);
        Employee employee=new Employee();
        employee.setEmpBatchID(employeeDTO.getEmpBatchID());
        employee.setFirstName(employeeDTO.getFirstName());
        return employee;
    }

}
