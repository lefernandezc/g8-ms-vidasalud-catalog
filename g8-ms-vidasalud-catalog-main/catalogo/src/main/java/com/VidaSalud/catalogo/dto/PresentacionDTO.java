package com.VidaSalud.catalogo.dto;

import lombok.Data;

@Data
public class PresentacionDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String boxCodigo;
    private Integer cuposDisponibles;
}