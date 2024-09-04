package com.guardianes.service.planta;

import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasUpdateRequestDTO;
import com.guardianes.dto.planta.PlantaResponseDTO;
import com.guardianes.dto.planta.PlantaSavingRequestDTO;
import com.guardianes.dto.planta.PlantaUpdateRequestDTO;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.entity.planta.Planta;
import com.guardianes.exception.RequestException;
import com.guardianes.repository.PlantaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantaService {
    private final PlantaRepository plantaRepository;
    private final PlantaMapper plantaMapper;

    public List<PlantaResponseDTO> findByAll() {
        return plantaRepository.findAll().stream()
                .map(plantaMapper::toPlantaResponseDTO).toList();
    }
    public Planta getPlantaById(Long id) {
        if (id == null || id == 0) {
            throw new RequestException("Id invalido!!!");
        }
        return plantaRepository.findById(id)
                .orElseThrow(() -> new RequestException("Planta no encontrada.!"));
    }
    public PlantaResponseDTO findByPlantaId(Long id) {
        if (id == null|| id == 0){
            throw new RequestException("Id invalido!!!");
        }
        Planta planta = plantaRepository.findById(id).orElseThrow(() -> new RequestException("Planta no encontrada.!"));
        return plantaMapper.toPlantaResponseDTO(planta);
    }
    public PlantaResponseDTO savePlanta(PlantaSavingRequestDTO plantaSavingRequestDTO) {
        verificarDatosRepetidos(plantaSavingRequestDTO);
        Planta planta = plantaMapper.plantaRequestToPost(plantaSavingRequestDTO);

        try {
            return plantaMapper.toPlantaResponseDTO(plantaRepository.save(planta));
        } catch (Exception e) {
            throw new RequestException("Error al guardar la planta: " + e.getMessage());
        }
    }
    private void verificarDatosRepetidos(PlantaSavingRequestDTO plantaSavingRequestDTO) {
        Optional<Planta> plantaOptional = plantaRepository.findByNombre(plantaSavingRequestDTO.getNombre());
        if (plantaOptional.isPresent()) {
            throw new RequestException("Nombre repetido!");
        }
        Optional<Planta> nombreCientificoOptional = plantaRepository.findByNombreCientifico(plantaSavingRequestDTO.getNombreCientifico());
        if (nombreCientificoOptional.isPresent()) {
            throw new RequestException("Nombre cientifico repetido!");
        }
    }

    public PlantaResponseDTO updatePlanta(Long id, PlantaUpdateRequestDTO plantaUpdateRequestDTO) throws BadRequestException {
        if (id == null || id <= 0){
            throw new BadRequestException("ID de Planta invalido");
        }
        Planta planta = plantaRepository.findById(id).orElseThrow(() -> new RuntimeException("Planta no encontrada con id: " + id));
        if (plantaUpdateRequestDTO.getNombre() != null) {
            planta.setNombre(plantaUpdateRequestDTO.getNombre());
        }
        if (plantaUpdateRequestDTO.getNombreCientifico() != null) {
            planta.setNombreCientifico(plantaUpdateRequestDTO.getNombreCientifico());
        }
        if (plantaUpdateRequestDTO.getEspecie() != null) {
            planta.setEspecie(plantaUpdateRequestDTO.getEspecie());
        }
        if (plantaUpdateRequestDTO.getEstimado_germinacion() != null) {
            planta.setEstimado_germinacion(plantaUpdateRequestDTO.getEstimado_germinacion());
        }
        if (plantaUpdateRequestDTO.getEstimado_plantula() != null) {
            planta.setEstimado_plantula(plantaUpdateRequestDTO.getEstimado_plantula());
        }
        if (plantaUpdateRequestDTO.getEstimado_crecimiento() != null) {
            planta.setEstimado_crecimiento(plantaUpdateRequestDTO.getEstimado_crecimiento());
        }
        if (plantaUpdateRequestDTO.getEstimado_madurez() != null) {
            planta.setEstimado_madurez(plantaUpdateRequestDTO.getEstimado_madurez());
        }

        return plantaMapper.toPlantaResponseDTO(plantaRepository.save(planta));
    }
}
