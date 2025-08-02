package com.WilsonQdop.Chamadas.services;

import com.WilsonQdop.Chamadas.dtos.calleddto.CalledRequestDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.CalledResponseDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.FinalizedCalledDTO;
import com.WilsonQdop.Chamadas.dtos.calleddto.FinalizedRequestDTO;
import com.WilsonQdop.Chamadas.enums.StatusEnum;
import com.WilsonQdop.Chamadas.exceptions.BusinessException;
import com.WilsonQdop.Chamadas.exceptions.CalledIsPaidException;
import com.WilsonQdop.Chamadas.exceptions.CalledNotFoundException;
import com.WilsonQdop.Chamadas.exceptions.TechnicalIsNotOwnerException;
import com.WilsonQdop.Chamadas.interfaces.CalledInterface;
import com.WilsonQdop.Chamadas.mappers.CalledMapper;
import com.WilsonQdop.Chamadas.models.Called;
import com.WilsonQdop.Chamadas.models.Customer;
import com.WilsonQdop.Chamadas.repositories.CalledRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CalledService implements CalledInterface {
    private final CalledRepository calledRepository;
    private final CustomerService customerService;
    private final CalledMapper mapper;

    public CalledService(CalledRepository calledRepository, CustomerService customerService,
                         CalledMapper mapper) {
        this.calledRepository = calledRepository;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public CalledResponseDTO create(CalledRequestDTO dto, UUID customerId) {
        Customer customer = this.customerService.findById(customerId);

        Called called = mapper.toDto(dto);

        switch (dto.category()) {
            case HIGH -> called.setValue(new BigDecimal(200));
            case MEDIA -> called.setValue(new BigDecimal(150));
            case LOW -> called.setValue(new BigDecimal(100));
        }
        called.setCustomer(customer);

        Called saved = this.calledRepository.save(called);

        return this.mapper.toEntity(saved);
    }

    @Override
    public List<CalledResponseDTO> findByStatusOpen() {
        List<Called> CalledOpen = this.calledRepository.findByStatusIn(List.of(StatusEnum.OPEN));

        return CalledOpen.stream().map(c -> new CalledResponseDTO(
                c.getTitle(),c.getDescription(), c.getValue(),c.isPaymentConfirmed(),
                c.getCategory(), c.getStatus(), c.getCreatedAt())).toList();
    }

    @Override
    public void paymentConfirmed(Long calledId) {
        Called called = this.findById(calledId);

        if (called.isPaymentConfirmed()) {
            throw new CalledIsPaidException();
        }
        called.setPaymentConfirmed(true);
        called.setStatus(StatusEnum.OPEN);

        this.calledRepository.save(called);
    }

    @Override
    public Called findById(Long id) {
        return this.calledRepository.findById(id)
                .orElseThrow(CalledNotFoundException::new);
    }

    @Override
    public FinalizedCalledDTO finalizedCalled(Long calledId, UUID technicalId, FinalizedRequestDTO dto) {
        Called called = this.findById(calledId);
        this.validateTechnicalOwnership(called, technicalId);

        this.isClosed(called);

        called.setStatus(dto.status());
        called.setCreatedAt(LocalDateTime.now());

        return this.mapper.mapToFinalizedResponse(this.calledRepository.save(called));
    }

    public void validateTechnicalOwnership(Called called, UUID technicalId) {
        if (called.getTechnical() != null && !technicalId.equals(called.getTechnical().getId())) {
            throw new TechnicalIsNotOwnerException();
        }
    }
    public void isClosed(Called called) {
       if (called.getStatus().equals(StatusEnum.COMPLETED ) || called.getStatus().equals(StatusEnum.CANCELED)) {
           throw new BusinessException();
        }
    }

}
