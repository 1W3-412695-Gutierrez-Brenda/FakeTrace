package com.faketrace.service;

import com.faketrace.repository.FuenteRepository;
import com.faketrace.repository.PublicacionRepository;
import com.faketrace.repository.TemaRepository;
import com.faketrace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final FuenteRepository fuenteRepository;
    private final TemaRepository temaRepository;

    public DashboardService(UsuarioRepository usuarioRepository,
                            PublicacionRepository publicacionRepository,
                            FuenteRepository fuenteRepository,
                            TemaRepository temaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        this.fuenteRepository = fuenteRepository;
        this.temaRepository = temaRepository;
    }

    // Devuelve las metricas globales del dashboard.
    public Map<String, Long> getMetricasGlobales() {
        Map<String, Long> metricas = new HashMap<>();
        metricas.put("totalUsuarios", usuarioRepository.count());
        metricas.put("totalPublicaciones", publicacionRepository.count());
        metricas.put("totalFuentes", fuenteRepository.count());
        metricas.put("totalTemas", temaRepository.count());
        return metricas;
    }

    // Devuelve las 3 publicaciones con mayor cantidad registrada de compartidos.
    public List<Map<String, Object>> getTop3PublicacionesMasCompartidas() {
        return publicacionRepository.findTop3PublicacionesMasCompartidas().stream()
                .map(this::unwrapRow)
                .toList();
    }

    // Devuelve la cantidad de publicaciones falsas agrupadas por tema para el grafico de barras.
    public List<Map<String, Object>> getPublicacionesFalsasPorTema() {
        return temaRepository.findCantidadPublicacionesFalsasPorTema().stream()
                .map(this::unwrapRow)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> unwrapRow(Map<String, Object> row) {
        Object value = row.get("row");
        if (value instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        return row;
    }
}
