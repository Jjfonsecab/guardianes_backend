package com.guardianes.controller;

import com.guardianes.dto.LoginRequestDTO;
import com.guardianes.dto.usuario.UsuarioResponseDTO;
import com.guardianes.dto.usuario.UsuarioSavingRequestDTO;
import com.guardianes.dto.usuario.UsuarioUpdateRequestDTO;
import com.guardianes.entity.usuario.Rol;
import com.guardianes.entity.usuario.Usuario;
import com.guardianes.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @GetMapping("/all")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findByAll());
    }

    @GetMapping("/active")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> getActiveUsuarios() {
        return ResponseEntity.ok(usuarioService.findActive());
    }
    @GetMapping("/{id}")
   // @PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findUsuarioConContraseñaId(id));
    }
    @PostMapping
    public ResponseEntity<?> saveUsuario(@Valid @RequestBody UsuarioSavingRequestDTO usuario, BindingResult result){
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            return ResponseEntity.ok(usuarioService.save(usuario));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Rol de usuario invalido")) {
                String acceptedValues = Arrays.stream(Rol.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                errorMessage = String.format("Valor invalido para rol de usuario. Los valores aceptados son: [%s]", acceptedValues);
            }
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", errorMessage));
        }
    }
    @PutMapping("{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE')")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequestDTO usuarioUpdate) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.update(id,usuarioUpdate));
    }
    @PatchMapping("/{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE')")
    public ResponseEntity<?> desactivarUsuario(@PathVariable Long id){
        usuarioService.desactivar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
