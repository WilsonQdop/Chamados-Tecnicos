package com.WilsonQdop.Chamadas.interfaces;

import com.WilsonQdop.Chamadas.dtos.customedto.CustomerRequestDTO;
import com.WilsonQdop.Chamadas.dtos.customedto.CustomerResponseDTO;
import com.WilsonQdop.Chamadas.models.Customer;

import java.util.UUID;

public interface CustomerInterface {
    CustomerResponseDTO save (CustomerRequestDTO dto);
    Customer findById(UUID id);
    CustomerResponseDTO update (CustomerRequestDTO dto, UUID id);
    void delete (UUID id);
}
