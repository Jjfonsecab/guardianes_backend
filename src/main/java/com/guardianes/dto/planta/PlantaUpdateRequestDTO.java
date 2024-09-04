package com.guardianes.dto.planta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantaUpdateRequestDTO {
    private Long id;
    private String nombre;
    private String nombreCientifico;
    private String especie;
    private BigDecimal estimado_germinacion;
    private BigDecimal estimado_plantula;
    private BigDecimal estimado_crecimiento;
    private BigDecimal estimado_madurez;
}
