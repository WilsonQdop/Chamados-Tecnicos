package com.WilsonQdop.Chamadas.interfaces;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalRequestDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalResponseDTO;
import com.WilsonQdop.Chamadas.models.Technical;

import java.util.UUID;

public interface TechnicalInterface {
    TechnicalResponseDTO save(TechnicalRequestDTO dto);
    Technical find(UUID id);
    TechnicalResponseDTO update(TechnicalRequestDTO dto, UUID id);
    void delete(UUID id);
    CalledResponseDTO assignToCalled(UUID technicalId, Long calledId);
}
