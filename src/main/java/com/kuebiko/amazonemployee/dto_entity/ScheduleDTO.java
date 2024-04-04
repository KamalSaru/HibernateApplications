package com.kuebiko.amazonemployee.dto_entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(schema = "amazon_company", name = "schedule_calculate")
@Data
@Getter
@Setter
@Entity

public class ScheduleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long scheduleID;
    private LocalDate startDate;
    private LocalDate endDate;
    public void totalDaysWork(){
        if (startDate != null && endDate != null) {
            //totalDaysWork = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        } else {
            //totalDaysWork = 0L; // Or any other default value indicating invalid dates
        }
    }

    private LocalTime punchIn;
    private LocalTime punchOut;

    public BigDecimal TotalHoursWorked(){
        if (punchIn !=null && punchOut !=null){
            Duration duration = Duration.between(punchIn, punchOut);
            Long seconds = duration.getSeconds();
            BigDecimal hours = BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(3600), 2, BigDecimal.ROUND_HALF_UP);
            return  hours;
        }else {
            return BigDecimal.ZERO;
        }
    }
}
