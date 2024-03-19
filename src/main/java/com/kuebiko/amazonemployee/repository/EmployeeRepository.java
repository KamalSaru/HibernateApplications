package com.kuebiko.amazonemployee.repository;

import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
    //In Postman---http://localhost:8080/employee/search/------------

    //@GetMapping-----Search/find employee details using first name-------------
    List<EmployeeDTO>findByFirstName(String firstName);

    //@GetMapping----getting method using empBatchID-------------------
    EmployeeDTO findByEmpBatchID(Long empBatchID);


    //@Request Param, @Request Path variable
    EmployeeDTO findByEmpBatchIDAndFirstName(Long empBatchID, String firstName);

}
