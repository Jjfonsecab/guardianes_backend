package com.guardianes.repository;

import com.guardianes.entity.planta.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {
    Optional<Planta> findByNombre(String nombre);

    Optional<Planta> findByNombreCientifico(String nombreCientifico);
}
