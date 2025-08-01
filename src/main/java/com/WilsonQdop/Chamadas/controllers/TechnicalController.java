package com.WilsonQdop.Chamadas.controllers;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalRequestDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalResponseDTO;
import com.WilsonQdop.Chamadas.models.Technical;
import com.WilsonQdop.Chamadas.services.TechnicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("technical")
public class TechnicalController {
    private final TechnicalService technicalService;

    public TechnicalController(TechnicalService technicalService) {
        this.technicalService = technicalService;
    }

    @PostMapping
    public ResponseEntity<TechnicalResponseDTO> save (@RequestBody TechnicalRequestDTO dto) {
        TechnicalResponseDTO technical = this.technicalService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(technical);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<TechnicalResponseDTO> update (@RequestBody TechnicalRequestDTO dto, @PathVariable UUID id) {
        TechnicalResponseDTO technical = this.technicalService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(technical);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<Technical> find (@PathVariable UUID id) {
        Technical technical = this.technicalService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(technical);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<TechnicalResponseDTO> delete (@PathVariable UUID id) {
         this.technicalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping("{technicalId}/assign/{calledId}")
    public ResponseEntity<CalledResponseDTO> assignToCalled (@PathVariable UUID technicalId, @PathVariable Long calledId) {
        CalledResponseDTO called = this.technicalService.assignToCalled(technicalId, calledId);
        return ResponseEntity.status(HttpStatus.OK).body(called);
    }

}
