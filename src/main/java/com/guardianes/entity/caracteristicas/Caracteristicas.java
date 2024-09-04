package com.guardianes.entity.caracteristicas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name= "caracteristicas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "hojas")
    private String hojas;
    @Column(name = "altura")
    private BigDecimal altura;
    @Column(name = "grosor")
    private BigDecimal grosor;
    @Column(name = "color")
    private String color;
}
