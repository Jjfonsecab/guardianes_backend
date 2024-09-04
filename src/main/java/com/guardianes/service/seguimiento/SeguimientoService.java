package com.guardianes.service.seguimiento;

import com.guardianes.dto.planta.PlantaResponseDTO;
import com.guardianes.dto.planta.PlantaSavingRequestDTO;
import com.guardianes.dto.planta.PlantaUpdateRequestDTO;
import com.guardianes.dto.seguimiento.SeguimientoResponseDTO;
import com.guardianes.dto.seguimiento.SeguimientoSavingRequestDTO;
import com.guardianes.dto.seguimiento.SeguimientoUpdateRequestDTO;
import com.guardianes.entity.ambiente.Ambiente;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.entity.planta.Planta;
import com.guardianes.entity.seguimiento.Seguimiento;
import com.guardianes.entity.usuario.Usuario;
import com.guardianes.exception.RequestException;
import com.guardianes.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeguimientoService {
    private final SeguimientoRepository seguimientoRepository;
    private final PlantaRepository plantaRepository;
    private final AmbienteRepository ambienteRepository;
    private final CaracteristicasRepository caracteristicasRepository;
    private final UsuarioRepository usuarioRepository;

    private final SeguimientoMapper seguimientoMapper;

    public List<SeguimientoResponseDTO> findByAll() {
        List<Seguimiento> seguimientos = seguimientoRepository.findAll();
        return seguimientoRepository.findAll().stream()
                .map(seguimientoMapper::toSeguimientoResponse).toList();
    }

    public SeguimientoResponseDTO findBySeguimientoId(Long id) {
        if (id == null|| id == 0){
            throw new RequestException("Id invalido!!!");
        }
        Seguimiento seguimiento = seguimientoRepository.findById(id).orElseThrow(() -> new RequestException("Reserva no encontrada.!"));
        return seguimientoMapper.toSeguimientoResponse(seguimiento);
    }

    public SeguimientoResponseDTO save(SeguimientoSavingRequestDTO seguimientoSavingRequestDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(seguimientoSavingRequestDTO.getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            throw new RequestException("El usuario con el ID proporcionado no existe.");
        }
        Usuario usuario = usuarioOptional.get();
        if (!usuario.isActive()) {
            throw new RequestException("El usuario con el ID proporcionado no está activo.");
        }
        Optional<Planta> plantaOptional = plantaRepository.findById(seguimientoSavingRequestDTO.getIdPlanta());
        if (!plantaOptional.isPresent()) {
            throw new RequestException("La planta con el ID proporcionado no existe.");
        }
        Optional<Ambiente> ambienteOptional = ambienteRepository.findById(seguimientoSavingRequestDTO.getIdAmbiente());
        if (!plantaOptional.isPresent()) {
            throw new RequestException("El ambiente con el ID proporcionado no existe.");
        }
        Optional<Caracteristicas> caracteristicasOptional = caracteristicasRepository.findById(seguimientoSavingRequestDTO.getIdCaracteristicas());
        if (!plantaOptional.isPresent()) {
            throw new RequestException("La caracteristica con el ID proporcionado no existe.");
        }
        Seguimiento seguimiento = seguimientoMapper.seguimientoRequestToPost(seguimientoSavingRequestDTO);

        try {
            return seguimientoMapper.toSeguimientoResponse(seguimientoRepository.save(seguimiento));
        } catch (Exception e) {
            throw new RequestException("Error al guardar el seguimiento: " + e.getMessage());
        }
    }
    public SeguimientoResponseDTO updateSeguimiento(Long id, SeguimientoUpdateRequestDTO seguimientoUpdateRequestDTO) throws BadRequestException {
        if (id == null || id <= 0){
            throw new BadRequestException("ID de Seguimiento invalido");
        }
        Seguimiento seguimiento = seguimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Seguimiento no encontrado con id: " + id));
        if (seguimientoUpdateRequestDTO.getComentarios() != null) {
            seguimiento.setComentarios(seguimientoUpdateRequestDTO.getComentarios());
        }

        return seguimientoMapper.toSeguimientoResponse(seguimientoRepository.save(seguimiento));
    }
}
