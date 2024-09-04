package com.guardianes.service.ambiente;

import com.guardianes.dto.ambiente.AmbienteResponseDTO;
import com.guardianes.dto.ambiente.AmbienteSavingRequestDTO;
import com.guardianes.entity.ambiente.Ambiente;
import com.guardianes.exception.RequestException;
import org.springframework.stereotype.Service;

@Service
public class AmbienteMapper {
    public AmbienteResponseDTO toAmbienteResponseDTO(Ambiente ambiente) {
        if(ambiente == null){
            throw new RequestException("Ambiente no puede ser nulo!");
        }
        return AmbienteResponseDTO.builder()
                .id(ambiente.getId())
                .temperatura(ambiente.getTemperatura())
                .humedad(ambiente.getHumedad())
                .suelo(ambiente.getSuelo())
                .acides(ambiente.getAcides())
                .adicionales(ambiente.getAdicionales())
                .build();
    }
    public Ambiente ambienteRequestToPost(AmbienteSavingRequestDTO ambiente){
        if (ambiente == null){
            throw new RequestException("Ambiente no puede ser nulo!!!");
        }
        return Ambiente.builder()
                .temperatura(ambiente.getTemperatura())
                .humedad(ambiente.getHumedad())
                .suelo(ambiente.getSuelo())
                .acides(ambiente.getAcides())
                .adicionales(ambiente.getAdicionales())
                .build();
    }
}
