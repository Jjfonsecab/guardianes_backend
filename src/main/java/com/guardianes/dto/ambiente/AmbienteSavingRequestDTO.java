package com.guardianes.dto.ambiente;

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
public class AmbienteSavingRequestDTO {
    @NotNull
    private BigDecimal temperatura;
    @NotNull
    private BigDecimal humedad;
    @NotBlank
    private String suelo;
    @NotBlank
    private String acides;
    @NotBlank
    private String adicionales;
}
