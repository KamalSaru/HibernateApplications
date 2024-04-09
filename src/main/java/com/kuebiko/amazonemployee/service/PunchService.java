package com.kuebiko.amazonemployee.service;


import com.kuebiko.amazonemployee.dao_repository.PunchRepository;
import com.kuebiko.amazonemployee.dto_entity.Punch;
import com.kuebiko.amazonemployee.enums.PunchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PunchService {

    @Autowired
    private PunchRepository punchRepository;

    public void punchIn(Punch punch) {
        //Punch punch = new Punch();
        punch.setTimestamp(LocalDateTime.now());
        punch.setType(PunchType.IN);
        punchRepository.save(punch);
    }

    public void punchOut() {
        Punch punch = new Punch();
        punch.setTimestamp(LocalDateTime.now());
        punch.setType(PunchType.OUT);
        punchRepository.save(punch);
    }

    public List<Punch> getPunchHistory() {
        return punchRepository.findAll();
    }
}
