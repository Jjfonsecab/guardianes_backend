package com.guardianes.repository;

import com.guardianes.entity.caracteristicas.Caracteristicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Long> {
}
