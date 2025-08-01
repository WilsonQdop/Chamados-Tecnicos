package com.WilsonQdop.Chamadas.interfaces;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.models.Called;


import java.util.List;
import java.util.UUID;

public interface CalledInterface {
    CalledResponseDTO create (CalledRequestDTO dto, UUID id);
    List<CalledResponseDTO> findByStatusOpen();
    void paymentConfirmed(Long calledId);
    Called findById (Long id);

}
