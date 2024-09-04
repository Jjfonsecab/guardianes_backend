package com.guardianes.controller;

import com.guardianes.dto.seguimiento.SeguimientoSavingRequestDTO;
import com.guardianes.dto.seguimiento.SeguimientoUpdateRequestDTO;
import com.guardianes.service.seguimiento.SeguimientoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seguimiento")
@CrossOrigin("*")
public class SeguimientoController {
    private final SeguimientoService seguimientoService;

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllSeguimientos() {
        return ResponseEntity.ok(seguimientoService.findByAll());
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<?> getSeguimientoById(@PathVariable Long id) {
        return ResponseEntity.ok(seguimientoService.findBySeguimientoId(id));
    }
    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> savePlanta(@Validated @RequestBody SeguimientoSavingRequestDTO seguimiento, BindingResult result) {
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            return ResponseEntity.ok(seguimientoService.save(seguimiento));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateSeguimiento(@PathVariable Long id, @Validated @RequestBody SeguimientoUpdateRequestDTO seguimientoUpdateRequestDTO) throws BadRequestException {
        return ResponseEntity.ok(seguimientoService.updateSeguimiento(id,seguimientoUpdateRequestDTO));
    }
}
