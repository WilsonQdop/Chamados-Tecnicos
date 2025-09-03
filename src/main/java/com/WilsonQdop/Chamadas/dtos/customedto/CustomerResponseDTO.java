package com.WilsonQdop.Chamadas.dtos.customedto;

import com.WilsonQdop.Chamadas.dtos.roledto.RoleDTO;

import java.util.Set;
import java.util.UUID;

public record CustomerResponseDTO(UUID id, String name, String email, String password, Set<RoleDTO> roles) {
}
