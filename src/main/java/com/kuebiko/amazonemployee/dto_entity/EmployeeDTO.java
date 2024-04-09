package com.kuebiko.amazonemployee.dto_entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(schema = "amazon_company", name="employee")

public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="ID_generator", sequenceName = "Employee_sequence")

    private Long ID;
    @Column(name = "emp_BatchID", unique = true)
    private Long empBatchID;

    //@NotBlank(message = "Please provide first name")
    private String firstName;
    private String lastName;
    @Column(name="date_of_birth")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @Transient //Age calculation
    //@Temporal(TemporalType.TIMESTAMP)
    //When getMapping we can see the age in year(2023-2000=23)--------------------
    private Integer age;
    public Integer getAge() {
        if (dob != null) {
            return Period.between(dob, LocalDate.now()).getYears();
        }
        return null;
    }

    private String position;
    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(name = "email", unique = true)
    //@Email(message = "Please provide email address")
    private String email;
    private String address;
    private String gender;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getEmpBatchID() {
        return empBatchID;
    }

    public void setEmpBatchID(Long empBatchID) {
        this.empBatchID = empBatchID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
