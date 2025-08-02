package com.WilsonQdop.Chamadas.dtos.calleddto;

import com.WilsonQdop.Chamadas.enums.StatusEnum;

import java.time.LocalDateTime;

public record FinalizedCalledDTO(LocalDateTime finishedAt, StatusEnum status) {
}
