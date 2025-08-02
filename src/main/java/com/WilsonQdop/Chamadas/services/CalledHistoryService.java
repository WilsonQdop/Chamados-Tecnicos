package com.WilsonQdop.Chamadas.services;

import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calledhistorydto.CalledHistoryResponseDTO;
import com.WilsonQdop.Chamadas.enums.StatusEnum;
import com.WilsonQdop.Chamadas.interfaces.CalledHistoryInterface;
import com.WilsonQdop.Chamadas.mappers.CalledHistoryMapper;
import com.WilsonQdop.Chamadas.models.Called;
import com.WilsonQdop.Chamadas.models.CalledHistory;
import com.WilsonQdop.Chamadas.models.Technical;
import com.WilsonQdop.Chamadas.repositories.CalledHistoryRepository;
import com.WilsonQdop.Chamadas.repositories.CalledRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CalledHistoryService implements CalledHistoryInterface {
    private final CalledService calledService;
    private final CalledHistoryRepository calledHistoryRepository;
    private final CalledHistoryMapper mapper;

    public CalledHistoryService(CalledService calledService, CalledHistoryRepository calledHistoryRepository,
                                CalledHistoryMapper mapper) {
        this.calledService = calledService;
        this.calledHistoryRepository = calledHistoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CalledHistoryResponseDTO registerObservation(CalledHistoryRequestDTO dto, UUID technicalId) {
        Called called = this.calledService.findById(dto.calledId());

        this.calledService.validateTechnicalOwnership(called, technicalId);

       this.calledService.isClosed(called);

        CalledHistory history = new CalledHistory();
        history.setObservation(dto.observation());
        history.setCalled(called);
        history.setTechnical(called.getTechnical());

        return this.mapper.toDto(this.calledHistoryRepository.save(history));
    }

}
