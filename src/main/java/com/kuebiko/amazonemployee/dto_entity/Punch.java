package com.kuebiko.amazonemployee.dto_entity;


import com.kuebiko.amazonemployee.enums.PunchType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(schema = "amazon_company", name="clock_in-out_record")
@Entity
@Getter
@Setter
public class Punch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private PunchType type;

}
