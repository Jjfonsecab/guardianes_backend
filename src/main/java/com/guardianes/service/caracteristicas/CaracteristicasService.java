package com.guardianes.service.caracteristicas;

import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasUpdateRequestDTO;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.exception.RequestException;
import com.guardianes.repository.CaracteristicasRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaracteristicasService {
    private final CaracteristicasRepository caracteristicasRepository;
    private final CaracteristicasMapper caracteristicasMapper;

    public List<CaracteristicasResponseDTO> findByAll() {
        return caracteristicasRepository.findAll().stream()
                .map(caracteristicasMapper::toCaracteristicasResponseDTO).toList();
    }
    public Caracteristicas getCaracteristicaById(Long id) {
        if (id == null || id == 0) {
            throw new RequestException("Id invalido!!!");
        }
        return caracteristicasRepository.findById(id)
                .orElseThrow(() -> new RequestException("Caracteristica no encontrada.!"));
    }
    public CaracteristicasResponseDTO findByCaracteristicaId(Long id) {
        if (id == null|| id == 0){
            throw new RequestException("Id invalido!!!");
        }
        Caracteristicas caracteristicas = caracteristicasRepository.findById(id).orElseThrow(() -> new RequestException("Caracteristica no encontrada.!"));
        return caracteristicasMapper.toCaracteristicasResponseDTO(caracteristicas);
    }
    public CaracteristicasResponseDTO saveCaracteristica(CaracteristicasSavingRequestDTO caracteristicasSavingRequestDTO) {

        Caracteristicas caracteristicas = caracteristicasMapper.caracteristicasRequestToPost(caracteristicasSavingRequestDTO);

        try {
            return caracteristicasMapper.toCaracteristicasResponseDTO(caracteristicasRepository.save(caracteristicas));
        } catch (Exception e) {
            throw new RequestException("Error al guardar la caracteristica: " + e.getMessage());
        }
    }
    public CaracteristicasResponseDTO updateCaracteristica(Long id, CaracteristicasUpdateRequestDTO caracteristicasUpdateRequestDTO) throws BadRequestException {
        if (id == null || id <= 0){
            throw new BadRequestException("ID de Caracteristica invalido");
        }
        Caracteristicas caracteristicas = caracteristicasRepository.findById(id).orElseThrow(() -> new RuntimeException("Caracteristica no encontrada con id: " + id));
        if (caracteristicasUpdateRequestDTO.getHojas() != null) {
            caracteristicas.setHojas(caracteristicasUpdateRequestDTO.getHojas());
        }
        if (caracteristicasUpdateRequestDTO.getAltura() != null) {
            caracteristicas.setAltura(caracteristicasUpdateRequestDTO.getAltura());
        }
        if (caracteristicasUpdateRequestDTO.getGrosor() != null) {
            caracteristicas.setGrosor(caracteristicasUpdateRequestDTO.getGrosor());
        }
        if (caracteristicasUpdateRequestDTO.getColor() != null) {
            caracteristicas.setColor(caracteristicasUpdateRequestDTO.getColor());
        }

        return caracteristicasMapper.toCaracteristicasResponseDTO(caracteristicasRepository.save(caracteristicas));
    }

}
