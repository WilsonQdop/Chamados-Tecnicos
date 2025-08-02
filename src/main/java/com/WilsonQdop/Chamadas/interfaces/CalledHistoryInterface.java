package com.WilsonQdop.Chamadas.interfaces;

import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryResponseDTO;

import java.util.UUID;

public interface CalledHistoryInterface {
    CalledHistoryResponseDTO registerObservation(CalledHistoryRequestDTO dto, UUID technicalId);
}
