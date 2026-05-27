package com.faketrace.service;

import com.faketrace.repository.PublicacionRepository;
import com.faketrace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

@Service
public class RastreoService {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    public RastreoService(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Devuelve la cadena completa de propagacion de una publicacion.
    public List<Map<String, Object>> getCadenaCompletaPropagacion(Long publicacionId) {
        List<Map<String, Object>> cadena = publicacionRepository.findCadenaTemporalByPublicacionId(publicacionId).stream()
                .map(this::unwrapRow)
                .<Map<String, Object>>map(row -> new HashMap<String, Object>(row))
                .toList();

        for (int i = 0; i < cadena.size(); i++) {
            cadena.get(i).put("saltos", i);
        }

        return cadena;
    }

    // Devuelve el camino mas corto entre dos usuarios usando las relaciones SIGUE cargadas desde el repository.
    public List<Map<String, Object>> getCaminoMasCortoEntreUsuarios(Long usuarioOrigenId, Long usuarioDestinoId) {
        Map<Long, Map<String, Object>> usuariosPorId = new HashMap<>();
        Map<Long, List<Long>> vecinosPorUsuario = new HashMap<>();

        usuarioRepository.findRelacionesSigue().stream()
                .map(this::unwrapRow)
                .forEach(row -> {
                    Map<String, Object> origen = extractMap(row.get("origen"));
                    Map<String, Object> destino = extractMap(row.get("destino"));

                    if (origen == null || destino == null) {
                        return;
                    }

                    Long origenId = toLong(origen.get("id"));
                    Long destinoId = toLong(destino.get("id"));
                    usuariosPorId.put(origenId, origen);
                    usuariosPorId.put(destinoId, destino);
                    vecinosPorUsuario.computeIfAbsent(origenId, key -> new ArrayList<>()).add(destinoId);
                });

        if (!usuariosPorId.containsKey(usuarioOrigenId) || !usuariosPorId.containsKey(usuarioDestinoId)) {
            return List.of();
        }

        Queue<Long> pendientes = new ArrayDeque<>();
        Set<Long> visitados = new HashSet<>();
        Map<Long, Long> anteriorPorUsuario = new HashMap<>();

        pendientes.add(usuarioOrigenId);
        visitados.add(usuarioOrigenId);

        while (!pendientes.isEmpty()) {
            Long actualId = pendientes.poll();

            if (actualId.equals(usuarioDestinoId)) {
                return reconstruirCamino(usuarioOrigenId, usuarioDestinoId, anteriorPorUsuario, usuariosPorId);
            }

            List<Long> vecinos = vecinosPorUsuario.getOrDefault(actualId, List.of());

            for (Long vecinoId : vecinos) {
                if (vecinoId == null || visitados.contains(vecinoId)) {
                    continue;
                }

                visitados.add(vecinoId);
                anteriorPorUsuario.put(vecinoId, actualId);
                pendientes.add(vecinoId);
            }
        }

        return List.of();
    }

    private List<Map<String, Object>> reconstruirCamino(Long origenId,
                                                        Long destinoId,
                                                        Map<Long, Long> anteriorPorUsuario,
                                                        Map<Long, Map<String, Object>> usuariosPorId) {
        List<Map<String, Object>> camino = new ArrayList<>();
        Long actualId = destinoId;

        while (actualId != null) {
            camino.add(0, usuariosPorId.get(actualId));

            if (actualId.equals(origenId)) {
                return camino;
            }

            actualId = anteriorPorUsuario.get(actualId);
        }

        return List.of();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> unwrapRow(Map<String, Object> row) {
        Object value = row.get("row");
        if (value instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        return row;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> extractMap(Object value) {
        if (value instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        return null;
    }

    private Long toLong(Object value) {
        if (value instanceof Number number) {
            return number.longValue();
        }
        return Long.valueOf(String.valueOf(value));
    }
}
