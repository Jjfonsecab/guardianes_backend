package com.guardianes.dto.caracteristicas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaracteristicasUpdateRequestDTO {
    private Long id;
    private String hojas;
    private BigDecimal altura;
    private BigDecimal grosor;
    private String color;
}
