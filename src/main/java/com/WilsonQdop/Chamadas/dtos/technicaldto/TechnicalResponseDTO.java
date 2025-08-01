package com.WilsonQdop.Chamadas.dtos.technicaldto;

import java.util.UUID;

public record TechnicalResponseDTO(UUID id, String name, String email, String password) {
}
