package com.WilsonQdop.Chamadas.dtos.calleddto;

import com.WilsonQdop.Chamadas.enums.CategoryEnum;
import com.WilsonQdop.Chamadas.enums.StatusEnum;

import java.util.UUID;

public record CalledRequestDTO(String title, String description, CategoryEnum category) {
}
