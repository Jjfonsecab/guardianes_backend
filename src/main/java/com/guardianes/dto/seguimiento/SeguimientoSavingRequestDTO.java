package com.guardianes.dto.seguimiento;

import com.guardianes.entity.seguimiento.Fase;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoSavingRequestDTO {
    @NotNull
    private Date fecha;
    @NotNull
    private long idPlanta;
    @NotNull
    private long idAmbiente;
    @NotNull
    private long idCaracteristicas;
    @NotNull
    private long idUsuario;
    @NotBlank
    private String comentarios;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Fase fase;
}
