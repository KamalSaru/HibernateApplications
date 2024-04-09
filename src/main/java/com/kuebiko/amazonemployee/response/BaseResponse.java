package com.kuebiko.amazonemployee.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private EmployeeDTOResponse data;
    private String message;
}
