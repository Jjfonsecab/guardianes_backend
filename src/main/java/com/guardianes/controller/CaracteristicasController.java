package com.guardianes.controller;

import com.guardianes.dto.ambiente.AmbienteResponseDTO;
import com.guardianes.dto.ambiente.AmbienteSavingRequestDTO;
import com.guardianes.dto.ambiente.AmbienteUpdateRequestDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasUpdateRequestDTO;
import com.guardianes.service.ambiente.AmbienteService;
import com.guardianes.service.caracteristicas.CaracteristicasService;
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
@RequestMapping("/api/caracteristicas")
@CrossOrigin("*")
public class CaracteristicasController {
    private final CaracteristicasService caracteristicasService;

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllCaracteristicas() {
        return ResponseEntity.ok(caracteristicasService.findByAll());
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<?> getCaracteristicaById(@PathVariable Long id) {
        return ResponseEntity.ok(caracteristicasService.findByCaracteristicaId(id));
    }
    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> saveCaracteristica(@Validated @RequestBody CaracteristicasSavingRequestDTO caracteristica, BindingResult result) {
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            return ResponseEntity.ok(caracteristicasService.saveCaracteristica(caracteristica));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateCaracteristica(@PathVariable Long id, @Validated @RequestBody CaracteristicasUpdateRequestDTO caracteristicasUpdateRequestDTO) throws BadRequestException {
        return ResponseEntity.ok(caracteristicasService.updateCaracteristica(id,caracteristicasUpdateRequestDTO));
    }
}
