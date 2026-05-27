package com.faketrace.controller;

import com.faketrace.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin(origins = "http://localhost:8080")
public class RankingController {

    private final RankingService rankingService;

    // Inyeccion por constructor.
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    // Devuelve el top 10 de usuarios superpropagadores.
    @GetMapping("/superpropagadores")
    public ResponseEntity<?> getSuperpropagadores() {
        List<Map<String, Object>> ranking = rankingService.getTop10Superpropagadores();
        return ResponseEntity.ok(ranking);
    }

    // Devuelve los usuarios detectados como bots.
    @GetMapping("/bots")
    public ResponseEntity<?> getBots() {
        List<Map<String, Object>> bots = rankingService.getUsuariosDetectadosComoBots();
        return ResponseEntity.ok(bots);
    }
}
