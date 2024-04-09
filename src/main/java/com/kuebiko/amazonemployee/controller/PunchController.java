package com.kuebiko.amazonemployee.controller;


import com.kuebiko.amazonemployee.dto_entity.Punch;
import com.kuebiko.amazonemployee.service.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PunchController {

    @Autowired
    private PunchService punchService;

    @PostMapping("/action/in")
    public ResponseEntity punchIn(@RequestBody Punch punch) {
        punchService.punchIn(punch);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/action/out")
    public ResponseEntity<Void> punchOut() {
        punchService.punchOut();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/action/history")
    public ResponseEntity<List<Punch>> getPunchHistory() {
        List<Punch> punchHistory = punchService.getPunchHistory();
        return ResponseEntity.ok(punchHistory);
    }
}
