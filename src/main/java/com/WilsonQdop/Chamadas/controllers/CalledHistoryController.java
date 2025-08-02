package com.WilsonQdop.Chamadas.controllers;

import com.WilsonQdop.Chamadas.dtos.calleddto.FinalizedCalledDTO;
import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryResponseDTO;
import com.WilsonQdop.Chamadas.enums.StatusEnum;
import com.WilsonQdop.Chamadas.models.CalledHistory;
import com.WilsonQdop.Chamadas.services.CalledHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("history")
public class CalledHistoryController {
    private final CalledHistoryService calledHistoryService;

    public CalledHistoryController(CalledHistoryService calledHistoryService) {
        this.calledHistoryService = calledHistoryService;
    }

    @PostMapping("registred/{technicalId}")
    public ResponseEntity<CalledHistoryResponseDTO> registredHistory (@RequestBody CalledHistoryRequestDTO dto,
                                                                     @PathVariable UUID technicalId) {
        CalledHistoryResponseDTO history = this.calledHistoryService.registerObservation(dto, technicalId );
        return ResponseEntity.ok().body(history);
    }

}
