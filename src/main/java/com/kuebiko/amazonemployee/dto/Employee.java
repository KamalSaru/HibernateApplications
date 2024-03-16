package com.kuebiko.amazonemployee.dto;

import jakarta.persistence.*;


@Entity
@Table(schema = "amazon_company", name="employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String empID;
    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private Long phoneNumber;
    private String email;
    private String address;
    private String gender;
}
