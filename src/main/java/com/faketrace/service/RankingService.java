package com.faketrace.service;

import com.faketrace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RankingService {

    private final UsuarioRepository usuarioRepository;

    public RankingService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Devuelve el top 10 de superpropagadores con su cantidad de publicaciones compartidas.
    public List<Map<String, Object>> getTop10Superpropagadores() {
        return usuarioRepository.findTop10SuperpropagadoresConCantidad().stream()
                .map(this::unwrapRow)
                .toList();
    }

    // Devuelve los usuarios detectados como bots por comportamiento coordinado.
    public List<Map<String, Object>> getUsuariosDetectadosComoBots() {
        return usuarioRepository.findUsuariosConComportamientoBot().stream()
                .map(this::unwrapRow)
                .map(row -> (Map<String, Object>) row.get("usuario"))
                .distinct()
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
