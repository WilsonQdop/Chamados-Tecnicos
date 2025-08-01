package com.WilsonQdop.Chamadas.services;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalRequestDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalResponseDTO;
import com.WilsonQdop.Chamadas.enums.StatusEnum;
import com.WilsonQdop.Chamadas.exceptions.CalledAssignmentException;
import com.WilsonQdop.Chamadas.exceptions.TechnicalNotFoundException;
import com.WilsonQdop.Chamadas.interfaces.TechnicalInterface;
import com.WilsonQdop.Chamadas.mappers.CalledMapper;
import com.WilsonQdop.Chamadas.mappers.TechnicalMapper;
import com.WilsonQdop.Chamadas.models.Called;
import com.WilsonQdop.Chamadas.models.Technical;
import com.WilsonQdop.Chamadas.repositories.CalledRepository;
import com.WilsonQdop.Chamadas.repositories.TechnicalRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TechnicalService implements TechnicalInterface {
    private final TechnicalRepository technicalRepository;
    private final CalledRepository calledRepository;
    private final CalledService calledService;
    private final TechnicalMapper mapper;
    private final CalledMapper mapperCalled;

    public TechnicalService(TechnicalRepository technicalRepository, CalledRepository calledRepository,
                            CalledService calledService, TechnicalMapper mapper, CalledMapper mapperCalled) {
        this.technicalRepository = technicalRepository;
        this.calledRepository = calledRepository;
        this.calledService = calledService;
        this.mapper = mapper;
        this.mapperCalled = mapperCalled;
    }

    @Override
    public TechnicalResponseDTO save(TechnicalRequestDTO dto) {

        Technical technical = this.mapper.toDto(dto);

        Technical saved = this.technicalRepository.save(technical);
        return this.mapper.toEntity(saved);
    }

    @Override
    public Technical find(UUID id) {
        return this.technicalRepository.findById(id)
                .orElseThrow(TechnicalNotFoundException::new);
    }

    @Override
    public TechnicalResponseDTO update(TechnicalRequestDTO dto, UUID id) {
        Technical technical = this.find(id);

        this.mapper.updateToEntityFromDto(dto, technical);
        Technical updated = this.technicalRepository.save(technical);
        return this.mapper.toEntity(updated);
    }

    @Override
    public void delete(UUID id) {
        Technical technical = this.find(id);
        this.technicalRepository.delete(technical);
    }

    @Override
    public CalledResponseDTO assignToCalled(UUID technicalId, Long calledId) {
        Called called = this.calledService.findById(calledId);
        Technical technical = this.find(technicalId);

        if (!called.isPaymentConfirmed() || !called.getStatus().equals(StatusEnum.OPEN) || called.getTechnical() != null) {
            throw new CalledAssignmentException();
        }

        called.setTechnical(technical);
        called.setStatus(StatusEnum.ASSIGNED);

        Called assignCalled = this.calledRepository.save(called);
        return this.mapperCalled.toEntity(assignCalled);
    }
}
