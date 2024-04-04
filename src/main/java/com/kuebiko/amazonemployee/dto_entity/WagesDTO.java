package com.kuebiko.amazonemployee.dto_entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(schema = "amazon_company", name = "wages_calculation")
@Data
@Getter
@Setter
@Entity

public class WagesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long wagesID;
    private BigDecimal hourlyRate;
    private Float totalWorkedTime;
    private Float bonusAmount;
    private Float overTimePay;
    private BigDecimal regularWages;
    private Float totalWages;
}
