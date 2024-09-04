package com.guardianes.dto.seguimiento;

import com.guardianes.entity.seguimiento.Fase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoUpdateRequestDTO {
    private String Comentarios;
}
