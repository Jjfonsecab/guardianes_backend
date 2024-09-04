package com.guardianes.service;

import com.guardianes.dto.LoginRequestDTO;
import com.guardianes.entity.Usuario;
import com.guardianes.exception.RequestException;
import com.guardianes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public Usuario login(LoginRequestDTO loginRquestDTO){
        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(loginRquestDTO.getEmail());
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.getContraseña().equals(loginRquestDTO.getPassword())) {
                return user;
            }
            else{
                throw  new RequestException("Contraseña invalida!");
            }
        }
        throw  new RequestException("Email incorrecto!");
    }

}
