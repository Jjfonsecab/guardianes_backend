package com.guardianes.repository;

import com.guardianes.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByActiveTrue();
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByDocumento(Long documento);
    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.active = true")
    Optional<Usuario> findActiveById(@Param("id") Long id);
}
