package com.guardianes.dto.usuario;

import com.guardianes.entity.usuario.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private long id;
    private String nombre;
    private String apellido;
    private long documento;
    private String email;
    private Rol rol;
    private boolean active;
}
