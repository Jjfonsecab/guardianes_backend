package com.guardianes.dto.caracteristicas;

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
public class CaracteristicasSavingRequestDTO {
    @NotBlank
    private String hojas;
    @NotNull
    private BigDecimal altura;
    @NotNull
    private BigDecimal grosor;
    @NotBlank
    private String color;
}
