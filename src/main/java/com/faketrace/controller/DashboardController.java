package com.faketrace.controller;

import com.faketrace.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:8080")
public class DashboardController {

    private final DashboardService dashboardService;

    // Inyeccion por constructor.
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Devuelve los totales globales de usuarios, publicaciones, fuentes y temas.
    @GetMapping("/metricas")
    public ResponseEntity<?> getMetricas() {
        Map<String, Long> metricas = dashboardService.getMetricasGlobales();
        return ResponseEntity.ok(metricas);
    }

    // Devuelve las 3 publicaciones mas compartidas.
    @GetMapping("/top-publicaciones")
    public ResponseEntity<?> getTopPublicaciones() {
        List<?> publicaciones = dashboardService.getTop3PublicacionesMasCompartidas();
        return ResponseEntity.ok(publicaciones);
    }

    // Devuelve la cantidad de publicaciones falsas agrupadas por tema.
    @GetMapping("/por-tema")
    public ResponseEntity<?> getPublicacionesFalsasPorTema() {
        List<Map<String, Object>> datos = dashboardService.getPublicacionesFalsasPorTema();
        return ResponseEntity.ok(datos);
    }
}
