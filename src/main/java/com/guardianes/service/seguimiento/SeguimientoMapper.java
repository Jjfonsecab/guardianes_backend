package com.guardianes.service.seguimiento;

import com.guardianes.dto.seguimiento.SeguimientoResponseDTO;
import com.guardianes.dto.seguimiento.SeguimientoSavingRequestDTO;
import com.guardianes.entity.ambiente.Ambiente;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.entity.planta.Planta;
import com.guardianes.entity.seguimiento.Seguimiento;
import com.guardianes.entity.usuario.Usuario;
import com.guardianes.exception.RequestException;
import com.guardianes.service.ambiente.AmbienteService;
import com.guardianes.service.caracteristicas.CaracteristicasService;
import com.guardianes.service.planta.PlantaService;
import com.guardianes.service.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class SeguimientoMapper {
    private PlantaService plantaService;
    private AmbienteService ambienteService;
    private CaracteristicasService caracteristicasService;
    private UsuarioService usuarioService;
    public SeguimientoResponseDTO toSeguimientoResponse(Seguimiento seguimiento) {
        if (seguimiento == null){
            throw new RequestException("Seguimiento no puede ser nulo!");
        }
        return SeguimientoResponseDTO.builder()
                .id(seguimiento.getId())
                .fecha(seguimiento.getFecha())
                .idUsuario(seguimiento.getUsuario().getId())
                .idPlanta(seguimiento.getPlanta().getId())
                .idAmbiente(seguimiento.getAmbiente().getId())
                .idCaracteristicas(seguimiento.getCaracteristicas().getId())
                .comentarios(seguimiento.getComentarios())
                .fase(seguimiento.getFase())
                .build();
    }

    public Seguimiento seguimientoRequestToPost(SeguimientoSavingRequestDTO seguimiento){
        if (seguimiento == null){
            throw new RequestException("Seguimiento no puede ser nulo!!!");
        }

        Usuario usuario = usuarioService.getUsuarioById(seguimiento.getIdUsuario());
        Planta planta = plantaService.getPlantaById(seguimiento.getIdPlanta());
        Ambiente ambiente = ambienteService.getAmbienteById(seguimiento.getIdAmbiente());
        Caracteristicas caracteristicas = caracteristicasService.getCaracteristicaById(seguimiento.getIdCaracteristicas());

        return Seguimiento.builder()
                .usuario(usuario)
                .fecha(seguimiento.getFecha())
                .planta(planta)
                .ambiente(ambiente)
                .caracteristicas(caracteristicas)
                .comentarios(seguimiento.getComentarios())
                .fase(seguimiento.getFase())
                .build();
    }
}
