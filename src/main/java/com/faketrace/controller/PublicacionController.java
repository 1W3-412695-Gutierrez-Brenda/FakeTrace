package com.faketrace.controller;

import com.faketrace.service.PublicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicacion")
@CrossOrigin(origins = "http://localhost:8080")
public class PublicacionController {

    private final PublicacionService publicacionService;

    // Inyeccion por constructor.
    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Devuelve los datos completos de una publicacion.
    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicacion(@PathVariable Long id) {
        Optional<Map<String, Object>> publicacion = publicacionService.getPublicacionById(id);
        return publicacion
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Devuelve el grafo de propagacion formateado para Vis.js.
    @GetMapping("/{id}/grafo")
    public ResponseEntity<?> getGrafo(@PathVariable Long id) {
        if (publicacionService.getPublicacionById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> grafo = publicacionService.getGrafoParaVisJs(id);
        return ResponseEntity.ok(grafo);
    }

    // Devuelve el nodo origen o paciente cero de una publicacion.
    @GetMapping("/{id}/origen")
    public ResponseEntity<?> getOrigen(@PathVariable Long id) {
        Optional<Map<String, Object>> origen = publicacionService.getOrigenPublicacion(id);
        return origen
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
