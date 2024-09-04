package com.guardianes.dto.planta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantaSavingRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String nombreCientifico;
    @NotBlank
    private String especie;
    @NotNull
    private BigDecimal estimado_germinacion;
    @NotNull
    private BigDecimal estimado_plantula;
    @NotNull
    private BigDecimal estimado_crecimiento;
    @NotNull
    private BigDecimal estimado_madurez;
}
