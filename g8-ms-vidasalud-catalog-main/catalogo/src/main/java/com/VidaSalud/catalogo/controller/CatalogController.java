package com.VidaSalud.catalogo.controller;

import com.VidaSalud.catalogo.dto.PresentacionDTO;
import com.VidaSalud.catalogo.entity.Presentacion;
import com.VidaSalud.catalogo.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/services")
    public List<Presentacion> obtenerServicios() {
        return catalogService.listarTodas();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Presentacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.obtenerPorId(id));
    }

    @PostMapping("/services")
    public ResponseEntity<Presentacion> crearServicio(@RequestBody PresentacionDTO dto) {
        return ResponseEntity.ok(catalogService.crear(dto));
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Presentacion> actualizar(@PathVariable Long id,
                                                  @RequestParam(required = false) Double precio,
                                                  @RequestParam(required = false) Integer cupos) {
        return ResponseEntity.ok(catalogService.actualizarPrecioYCupos(id, precio, cupos));
    }

    @PutMapping("/services/{id}/decrease-slot")
    public ResponseEntity<Void> descontarCupo(@PathVariable Long id) {
        catalogService.descontarCupo(id);
        return ResponseEntity.ok().build();
    }
}