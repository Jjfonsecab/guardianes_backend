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
public class CaracteristicasResponseDTO {
    private long id;
    private String hojas;
    private BigDecimal altura;
    private BigDecimal grosor;
    private String color;
}
