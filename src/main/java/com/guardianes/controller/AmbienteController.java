package com.guardianes.controller;

import com.guardianes.dto.ambiente.AmbienteResponseDTO;
import com.guardianes.dto.ambiente.AmbienteSavingRequestDTO;
import com.guardianes.dto.ambiente.AmbienteUpdateRequestDTO;
import com.guardianes.service.ambiente.AmbienteService;
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
@RequestMapping("/api/ambiente")
@CrossOrigin("*")
public class AmbienteController {
    private final AmbienteService ambienteService;
    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllAmbientes() {
        return ResponseEntity.ok(ambienteService.findByAll());
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<?> getAmbienteById(@PathVariable Long id) {
        return ResponseEntity.ok(ambienteService.findByAmbienteId(id));
    }
    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> saveAmbiente(@Validated @RequestBody AmbienteSavingRequestDTO ambiente, BindingResult result) {
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            return ResponseEntity.ok(ambienteService.saveAmbiente(ambiente));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateAmbiente(@PathVariable Long id, @Validated @RequestBody AmbienteUpdateRequestDTO ambienteUpdateRequestDTO) throws BadRequestException {
        return ResponseEntity.ok(ambienteService.updateAmbiente(id,ambienteUpdateRequestDTO));
    }
}
