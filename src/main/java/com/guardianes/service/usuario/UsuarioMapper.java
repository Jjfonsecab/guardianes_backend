package com.guardianes.service.usuario;

import com.guardianes.dto.usuario.UsuarioContraseñaResponse;
import com.guardianes.dto.usuario.UsuarioResponseDTO;
import com.guardianes.dto.usuario.UsuarioSavingRequestDTO;
import com.guardianes.entity.usuario.Usuario;
import com.guardianes.exception.RequestException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {
    public UsuarioResponseDTO toUsuarioResponse(Usuario usuario){
        if(usuario == null){
            throw new RequestException("Usuario no puede ser nulo!");
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .documento(usuario.getDocumento())
                .rol(usuario.getRol())
                .active(usuario.isActive())
                .build();
    }

    public UsuarioContraseñaResponse toUsuarioConContrasenaResponse(Usuario usuario) {
        if (usuario == null) {
            throw new RequestException("Usuario no puede ser nulo!");
        }
        return UsuarioContraseñaResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .contraseña(usuario.getContraseña())
                .documento(usuario.getDocumento())
                .rol(usuario.getRol())
                .active(usuario.isActive())
                .build();
    }

    public Usuario usuarioRequestToPost(UsuarioSavingRequestDTO usuario){
        if (usuario == null){
            throw new RequestException("Usuario no puede ser nulo!!!");
        }
        return Usuario.builder()
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .contraseña(usuario.getContraseña())
                .documento(usuario.getDocumento())
                .rol(usuario.getRol())
                .build();
    }
}
