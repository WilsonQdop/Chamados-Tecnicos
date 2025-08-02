package com.WilsonQdop.Chamadas.controllers;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.FinalizedCalledDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.FinalizedRequestDTO;
import com.WilsonQdop.Chamadas.enums.StatusEnum;
import com.WilsonQdop.Chamadas.services.CalledService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("called")
public class CalledController {
    private final CalledService calledService;

    public CalledController(CalledService calledService) {
        this.calledService = calledService;
    }

    @PostMapping("create/{customerId}")
    public ResponseEntity<CalledResponseDTO> create (@RequestBody CalledRequestDTO dto, @PathVariable UUID customerId) {
        CalledResponseDTO called = this.calledService.create(dto, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(called);
    }

    @GetMapping("status/open")
    public ResponseEntity<List<CalledResponseDTO>> findStausOpen () {
        List<CalledResponseDTO> calledStatus = this.calledService.findByStatusOpen();
        return ResponseEntity.status(HttpStatus.OK).body(calledStatus);
    }
    @PutMapping("payment/{calledId}")
    public ResponseEntity<Void> paymentConfirmed (@PathVariable  Long calledId) {
        this.calledService.paymentConfirmed(calledId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{technicalId}/finalized/{calledId}")
    public ResponseEntity<FinalizedCalledDTO> finalizedCalled (@PathVariable Long calledId, @PathVariable UUID technicalId,
                                                               @RequestBody FinalizedRequestDTO dto ) {

        FinalizedCalledDTO response = this.calledService.finalizedCalled(calledId, technicalId, dto);
        return ResponseEntity.ok(response);
    }

}
