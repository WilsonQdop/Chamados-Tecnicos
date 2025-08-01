package com.WilsonQdop.Chamadas.mappers;

import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalRequestDTO;
import com.WilsonQdop.Chamadas.dtos.technicaldto.TechnicalResponseDTO;
import com.WilsonQdop.Chamadas.models.Technical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TechnicalMapper {
    TechnicalResponseDTO toEntity(Technical technical);
    Technical toDto(TechnicalRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateToEntityFromDto(TechnicalRequestDTO dto, @MappingTarget Technical technical);
}
