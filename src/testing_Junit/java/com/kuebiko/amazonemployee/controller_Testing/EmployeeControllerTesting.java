package com.kuebiko.amazonemployee.controller_Testing;

import com.kuebiko.amazonemployee.controller.EmployeeController;
import com.kuebiko.amazonemployee.dao_repository.EmployeeRepository;
import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;
import com.kuebiko.amazonemployee.request.EmployeeDTORequest;
import com.kuebiko.amazonemployee.response.EmployeeDTOResponse;
import com.kuebiko.amazonemployee.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeControllerTesting {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before //before execution of each test case
    public void setUp(){MockitoAnnotations.initMocks(this);}

    //This is the input request payload from EmployeeRequest
    private EmployeeDTORequest getEmployeeDTORequest(){
        EmployeeDTORequest employeeDTORequest =new EmployeeDTORequest();
        //employeeDTORequest.setID(1L);
        employeeDTORequest.setEmpBatchID(101L); //empBatchID long
        employeeDTORequest.setFirstName("Kamal");
        employeeDTORequest.setLastName("Saru");
        employeeDTORequest.setDob(LocalDate.ofEpochDay(1989-01-01));
        employeeDTORequest.setAge(34);
        employeeDTORequest.setPosition("Software Engineering");
        employeeDTORequest.setPhoneNumber(441231456L); //Cast in Long l
        employeeDTORequest.setEmail("kamal@gmail.com");
        employeeDTORequest.setAddress("Baltimore MD");
        employeeDTORequest.setGender("Male");
        return employeeDTORequest;
    }

    private EmployeeDTOResponse getEmployeeDTOResponse(){
        EmployeeDTOResponse employeeDTOResponse = new EmployeeDTOResponse();
        //employeeDTOResponse.setID(1L);
        employeeDTOResponse.setFirstName("Kamal");
        employeeDTOResponse.setEmail("kamal@gmail.com");
        employeeDTOResponse.setPhoneNumber(441231456L);
        return employeeDTOResponse;

    }

    private EmployeeDTO getEmployeeByID(){
        EmployeeDTO employeeDTO =new EmployeeDTO();
        employeeDTO.setID(1L);
        employeeDTO.setEmpBatchID(101L);
        employeeDTO.setFirstName("Kamal");
        employeeDTO.setEmail("kamal@gmail.com");
        employeeDTO.setPhoneNumber(44312456L);
        return employeeDTO;
    }


    @Test
    //Testing for save employee details------
    public void saveEmployeeTestPositive(){
        Mockito.doNothing().when(employeeService).saveEmployee(any(EmployeeDTO.class));
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setID(10L);
        employeeDTO.setEmpBatchID(101L);
        employeeDTO.setFirstName("Kamal");
        employeeDTO.setGender("Male");
        // Mock behavior for employeeService.saveEmployee

        // Call the controller method
        ResponseEntity<String> actualResponse =employeeController.saveEmployee(employeeDTO);
        // Verify the response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Employee details save successfully.", actualResponse.getBody());
    }


    @Test
    //Testing find the employee by ID-----
    public void getEmployeeByIDPositive(){
        EmployeeDTO getEmployeeResponse = new EmployeeDTO();

        Mockito.when(employeeService.getEmployeeByID(Mockito.anyLong())).thenReturn((getEmployeeByID()));
        ResponseEntity<?> actualResponse = employeeController.getEmployeeByID(101L);
        assertNotNull(actualResponse);
        assertEquals(getEmployeeByID(), actualResponse.getBody());
    }

    @Test
    public void getEmployeeByIDNegative(){
        Mockito.when(employeeService.getEmployeeByID(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> actualResponse = employeeController.getEmployeeByID(101L);
        assertNotNull(actualResponse);
    }

    @Test
    public void getAllEmployeeDetailsTestPositive(){
        List<EmployeeDTO> employeeDTOList =new ArrayList<>();
    }
}
