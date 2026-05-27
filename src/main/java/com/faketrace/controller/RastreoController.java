package com.faketrace.controller;

import com.faketrace.service.RastreoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rastreo")
@CrossOrigin(origins = "http://localhost:8080")
public class RastreoController {

    private final RastreoService rastreoService;

    // Inyeccion por constructor.
    public RastreoController(RastreoService rastreoService) {
        this.rastreoService = rastreoService;
    }

    // Devuelve la cadena completa de propagacion de una publicacion.
    @GetMapping("/{id}/cadena")
    public ResponseEntity<?> getCadena(@PathVariable Long id) {
        List<Map<String, Object>> cadena = rastreoService.getCadenaCompletaPropagacion(id);
        if (cadena.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cadena);
    }

    // Devuelve el camino mas corto entre dos usuarios.
    @GetMapping("/camino")
    public ResponseEntity<?> getCamino(@RequestParam Long desde, @RequestParam Long hasta) {
        List<Map<String, Object>> camino = rastreoService.getCaminoMasCortoEntreUsuarios(desde, hasta);
        if (camino.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(camino);
    }
}
