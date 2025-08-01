package com.WilsonQdop.Chamadas.dtos.customedto;

import java.util.UUID;

public record CustomerResponseDTO(UUID id, String name, String email, String password) {
}
