package com.kuebiko.amazonemployee.repository;

import com.kuebiko.amazonemployee.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
