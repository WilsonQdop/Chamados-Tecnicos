package com.WilsonQdop.Chamadas.dtos.calledhistorydto;

import java.time.LocalDateTime;

public record CalledHistoryResponseDTO (String observation, String technicalName, LocalDateTime createdAt){
}
