package com.guardianes.entity.ambiente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name= "ambiente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "temperatura")
    private BigDecimal temperatura;
    @Column(name = "humedad")
    private BigDecimal humedad;
    @Column(name = "suelo")
    private String suelo;
    @Column(name = "acides")
    private String acides;
    @Column(name = "adicionales")
    private String adicionales;
}
