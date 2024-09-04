package com.guardianes.service.caracteristicas;

import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.exception.RequestException;
import org.springframework.stereotype.Service;

@Service
public class CaracteristicasMapper {
    public CaracteristicasResponseDTO toCaracteristicasResponseDTO(Caracteristicas caracteristicas) {
        if(caracteristicas == null){
            throw new RequestException("Caracteristicas no puede ser nulo!");
        }
        return CaracteristicasResponseDTO.builder()
                .id(caracteristicas.getId())
                .hojas(caracteristicas.getHojas())
                .altura(caracteristicas.getAltura())
                .grosor(caracteristicas.getGrosor())
                .color(caracteristicas.getColor())
                .build();
    }

    public Caracteristicas caracteristicasRequestToPost(CaracteristicasSavingRequestDTO caracteristicas){
        if (caracteristicas == null){
            throw new RequestException("Caracteristicas no puede ser nulo!!!");
        }
        return Caracteristicas.builder()
                .hojas(caracteristicas.getHojas())
                .altura(caracteristicas.getAltura())
                .grosor(caracteristicas.getGrosor())
                .color(caracteristicas.getColor())
                .build();
    }
}
