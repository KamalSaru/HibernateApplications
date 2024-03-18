package com.kuebiko.amazonemployee.repository;

import com.kuebiko.amazonemployee.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
    //@GetMapping-----Search/find employee details using @Request param-------------
    //In Postman---http://localhost:8080/employee/search/------------

    List<EmployeeDTO>findByFirstName(String firstName);

    EmployeeDTO findByEmpBatchID(Long empBatchID);

    EmployeeDTO findByEmpBatchIDAndFirstName(Long empBatchID, String firstName);

}
