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
public class SeguimientoResponseDTO {
    private long id;
    private Date fecha;
    private long idPlanta;
    private long idAmbiente;
    private long idCaracteristicas;
    private long idUsuario;
    private String comentarios;
    private Fase fase;
}
