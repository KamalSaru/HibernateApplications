package com.kuebiko.amazonemployee.repository;

import com.kuebiko.amazonemployee.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
    EmployeeDTO findByEmpBatchID(Long empBatchID);

    EmployeeDTO findByEmpBatchIDAndFirstName(Long empBatchID, String firstName);

}
