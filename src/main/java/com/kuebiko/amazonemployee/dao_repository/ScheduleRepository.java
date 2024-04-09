package com.kuebiko.amazonemployee.dao_repository;

import com.kuebiko.amazonemployee.dto_entity.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository <ScheduleDTO, Long> {



    //@OneToOne(fetch= FetchType.EAGER)
    //@JoinColumn(name="PunchID"
    //private

    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    //@JoinColumn(name="EmpID")

    //@OneToOne(mappedBy = "employee", cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    //private Punch punch;

    //@Enumerated(EnumType.STRING)
    //private PositionTypes positionTypes;

}
