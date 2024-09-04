package com.guardianes.service.ambiente;

import com.guardianes.dto.ambiente.AmbienteResponseDTO;
import com.guardianes.dto.ambiente.AmbienteSavingRequestDTO;
import com.guardianes.dto.ambiente.AmbienteUpdateRequestDTO;
import com.guardianes.entity.ambiente.Ambiente;
import com.guardianes.exception.RequestException;
import com.guardianes.repository.AmbienteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmbienteService {
    private final AmbienteRepository ambienteRepository;
    private final AmbienteMapper ambienteMapper;

    public List<AmbienteResponseDTO> findByAll() {
        return ambienteRepository.findAll().stream()
                .map(ambienteMapper::toAmbienteResponseDTO).toList();
    }
    public Ambiente getAmbienteById(Long id){
        if (id == null || id == 0) {
            throw new RequestException("Id invalido!!!");
        }
        return ambienteRepository.findById(id)
                .orElseThrow(() -> new RequestException("Ambiente no encontrado.!"));
    }

    public AmbienteResponseDTO findByAmbienteId(Long id) {
        if (id == null|| id == 0){
            throw new RequestException("Id invalido!!!");
        }
        Ambiente ambiente = ambienteRepository.findById(id).orElseThrow(() -> new RequestException("Ambiente no encontrado.!"));
        return ambienteMapper.toAmbienteResponseDTO(ambiente);
    }
    public AmbienteResponseDTO saveAmbiente(AmbienteSavingRequestDTO ambienteSavingRequestDTO) {
        Ambiente ambiente = ambienteMapper.ambienteRequestToPost(ambienteSavingRequestDTO);

        try {
            return ambienteMapper.toAmbienteResponseDTO(ambienteRepository.save(ambiente));
        } catch (Exception e) {
            throw new RequestException("Error al guardar el ambiente: " + e.getMessage());
        }
    }
    public AmbienteResponseDTO updateAmbiente(Long id, AmbienteUpdateRequestDTO ambienteUpdateRequestDTO) throws BadRequestException {
        if (id == null || id <= 0){
            throw new BadRequestException("ID de ambiente invalido");
        }
        Ambiente ambiente = ambienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Ambiente no encontrado con id: " + id));
        if (ambienteUpdateRequestDTO.getTemperatura() != null) {
            ambiente.setTemperatura(ambienteUpdateRequestDTO.getTemperatura());
        }
        if (ambienteUpdateRequestDTO.getHumedad() != null) {
            ambiente.setHumedad(ambienteUpdateRequestDTO.getHumedad());
        }
        if (ambienteUpdateRequestDTO.getSuelo() != null) {
            ambiente.setSuelo(ambienteUpdateRequestDTO.getSuelo());
        }
        if (ambienteUpdateRequestDTO.getAcides() != null) {
            ambiente.setAcides(ambienteUpdateRequestDTO.getAcides());
        }
        if (ambienteUpdateRequestDTO.getAdicionales() != null) {
            ambiente.setAdicionales(ambienteUpdateRequestDTO.getAdicionales());
        }

        return ambienteMapper.toAmbienteResponseDTO(ambienteRepository.save(ambiente));
    }
}
