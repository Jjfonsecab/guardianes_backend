package com.guardianes.controller;

import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasUpdateRequestDTO;
import com.guardianes.dto.planta.PlantaResponseDTO;
import com.guardianes.dto.planta.PlantaSavingRequestDTO;
import com.guardianes.dto.planta.PlantaUpdateRequestDTO;
import com.guardianes.service.caracteristicas.CaracteristicasService;
import com.guardianes.service.planta.PlantaService;
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
@RequestMapping("/api/plantas")
@CrossOrigin("*")
public class PlantaController {
    private final PlantaService plantaService;

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllPlantas() {
        return ResponseEntity.ok(plantaService.findByAll());
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<?> getPlantaById(@PathVariable Long id) {
        return ResponseEntity.ok(plantaService.findByPlantaId(id));
    }
    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> savePlanta(@Validated @RequestBody PlantaSavingRequestDTO planta, BindingResult result) {
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            return ResponseEntity.ok(plantaService.savePlanta(planta));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updatePlanta(@PathVariable Long id, @Validated @RequestBody PlantaUpdateRequestDTO plantaUpdateRequestDTO) throws BadRequestException {
        return ResponseEntity.ok(plantaService.updatePlanta(id,plantaUpdateRequestDTO));
    }
}
