package com.guardianes.dto.ambiente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmbienteUpdateRequestDTO {
    private Long id;
    private BigDecimal temperatura;
    private BigDecimal humedad;
    private String suelo;
    private String acides;
    private String adicionales;
}
