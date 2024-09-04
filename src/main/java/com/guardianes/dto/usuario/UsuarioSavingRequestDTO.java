package com.guardianes.dto.usuario;

import com.guardianes.entity.usuario.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSavingRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    private long documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String contraseña;
    @org.antlr.v4.runtime.misc.NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
