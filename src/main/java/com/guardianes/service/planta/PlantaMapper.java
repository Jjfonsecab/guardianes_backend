package com.guardianes.service.planta;

import com.guardianes.dto.caracteristicas.CaracteristicasResponseDTO;
import com.guardianes.dto.caracteristicas.CaracteristicasSavingRequestDTO;
import com.guardianes.dto.planta.PlantaResponseDTO;
import com.guardianes.dto.planta.PlantaSavingRequestDTO;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.entity.planta.Planta;
import com.guardianes.exception.RequestException;
import org.springframework.stereotype.Service;

@Service
public class PlantaMapper {
    public PlantaResponseDTO toPlantaResponseDTO(Planta planta) {
        if(planta == null){
            throw new RequestException("Planta no puede ser nulo!");
        }
        return PlantaResponseDTO.builder()
                .id(planta.getId())
                .nombre(planta.getNombre())
                .nombreCientifico(planta.getNombreCientifico())
                .especie(planta.getEspecie())
                .estimado_germinacion(planta.getEstimado_germinacion())
                .estimado_plantula(planta.getEstimado_plantula())
                .estimado_crecimiento(planta.getEstimado_crecimiento())
                .estimado_madurez(planta.getEstimado_madurez())
                .build();
    }

    public Planta plantaRequestToPost(PlantaSavingRequestDTO planta){
        if (planta == null){
            throw new RequestException("Planta no puede ser nulo!!!");
        }
        return Planta.builder()
                .nombre(planta.getNombre())
                .nombreCientifico(planta.getNombreCientifico())
                .especie(planta.getEspecie())
                .estimado_germinacion(planta.getEstimado_germinacion())
                .estimado_plantula(planta.getEstimado_plantula())
                .estimado_crecimiento(planta.getEstimado_crecimiento())
                .estimado_madurez(planta.getEstimado_madurez())
                .build();
    }
}
