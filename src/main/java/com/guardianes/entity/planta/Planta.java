package com.guardianes.entity.planta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name= "planta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "nombre_cientifico", unique = true)
    private String nombreCientifico;
    @Column(name = "especie")
    private String especie;
    @Column(name = "estimado_germinacion")
    private BigDecimal estimado_germinacion;
    @Column(name = "estimado_plantula")
    private BigDecimal estimado_plantula;
    @Column(name = "estimado_crecimiento")
    private BigDecimal estimado_crecimiento;
    @Column(name = "estimado_madurez")
    private BigDecimal estimado_madurez;
}
