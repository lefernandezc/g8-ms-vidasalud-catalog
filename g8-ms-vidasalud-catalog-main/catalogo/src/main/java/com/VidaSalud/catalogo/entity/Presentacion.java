package com.VidaSalud.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catalog_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Presentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    private String boxCodigo;

    @Column(nullable = false)
    private Integer cuposDisponibles;
}
