package com.guardianes.entity.seguimiento;

import com.guardianes.entity.ambiente.Ambiente;
import com.guardianes.entity.caracteristicas.Caracteristicas;
import com.guardianes.entity.planta.Planta;
import com.guardianes.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "seguimiento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Seguimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "fecha")
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "id_planta", referencedColumnName = "id")
    private Planta planta;
    @OneToOne
    @JoinColumn(name = "id_ambiente", referencedColumnName = "id")
    private Ambiente ambiente;
    @OneToOne
    @JoinColumn(name = "id_caracteristica", referencedColumnName = "id")
    private Caracteristicas caracteristicas;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @Column(name = "comentarios")
    private String comentarios;
    @Enumerated(EnumType.STRING)
    private Fase fase;
}
