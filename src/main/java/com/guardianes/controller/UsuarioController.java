package com.guardianes.controller;

import com.guardianes.dto.LoginRequestDTO;
import com.guardianes.dto.user.UserResponseDTO;
import com.guardianes.entity.Usuario;
import com.guardianes.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDto) {
        Usuario authenticatedUser = usuarioService.login(loginRequestDto);
        if (authenticatedUser != null) {
            UserResponseDTO userResponseDto = new UserResponseDTO();
            userResponseDto.setId(String.valueOf(authenticatedUser.getId()));
            userResponseDto.setNombre(authenticatedUser.getNombre());
            userResponseDto.setApellido(authenticatedUser.getApellido());
            userResponseDto.setEmail(authenticatedUser.getEmail());
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        throw new RuntimeException();
    }

}
