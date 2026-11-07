package com.VidaSalud.catalogo.service;

import com.VidaSalud.catalogo.dto.PresentacionDTO;
import com.VidaSalud.catalogo.entity.Presentacion;
import com.VidaSalud.catalogo.repository.PresentacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final PresentacionRepository repository;

    public CatalogService(PresentacionRepository repository) {
        this.repository = repository;
    }

    public List<Presentacion> listarTodas() {
        return repository.findAll();
    }

    public Presentacion obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestación no encontrada"));
    }

    public Presentacion crear(PresentacionDTO dto) {
        Presentacion prestacion = Presentacion.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .boxCodigo(dto.getBoxCodigo())
                .cuposDisponibles(dto.getCuposDisponibles())
                .build();
        return repository.save(prestacion);
    }

    public Presentacion actualizarPrecioYCupos(Long id, Double precio, Integer cupos) {
        Presentacion prestacion = obtenerPorId(id);
        if (precio != null) prestacion.setPrecio(precio);
        if (cupos != null) prestacion.setCuposDisponibles(cupos);
        return repository.save(prestacion);
    }

    public void descontarCupo(Long id) {
        Presentacion prestacion = obtenerPorId(id);
        if (prestacion.getCuposDisponibles() <= 0) {
            throw new IllegalStateException("No hay cupos disponibles para esta prestación.");
        }
        prestacion.setCuposDisponibles(prestacion.getCuposDisponibles() - 1);
        repository.save(prestacion);
    }
}