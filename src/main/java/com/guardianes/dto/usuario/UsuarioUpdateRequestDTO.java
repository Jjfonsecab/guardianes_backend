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
public class UsuarioUpdateRequestDTO {
    private String id;
    private String nombre;
    private String apellido;
    private long documento;
    private String email;
    private String contraseña;
    private Rol rol;
    private boolean active;
}
