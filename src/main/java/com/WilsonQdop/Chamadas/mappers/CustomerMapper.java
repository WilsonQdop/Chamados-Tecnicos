package com.WilsonQdop.Chamadas.mappers;


import com.WilsonQdop.Chamadas.dtos.customedto.CustomerRequestDTO;
import com.WilsonQdop.Chamadas.dtos.customedto.CustomerResponseDTO;
import com.WilsonQdop.Chamadas.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDto(CustomerRequestDTO dto);
    CustomerResponseDTO toEntity (Customer customer);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CustomerRequestDTO dto, @MappingTarget Customer customer);
}
