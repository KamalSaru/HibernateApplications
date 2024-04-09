package com.kuebiko.amazonemployee.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTOResponse {

    private Long empBatchID;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Integer age;
    private String position;
    private Long phoneNumber;
    private String email;
    private String address;
    private String gender;
}
