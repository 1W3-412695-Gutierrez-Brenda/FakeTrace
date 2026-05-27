package com.faketrace.service;

import com.faketrace.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    // Devuelve los datos completos de una publicacion por id.
    public Optional<Map<String, Object>> getPublicacionById(Long id) {
        return publicacionRepository.findDetalleByPublicacionId(id).stream()
                .findFirst()
                .map(this::unwrapRow);
    }

    // Formatea el grafo de propagacion para ser consumido por Vis.js.
    public Map<String, Object> getGrafoParaVisJs(Long id) {
        List<Map<String, Object>> rawData = publicacionRepository.findCadenaTemporalByPublicacionId(id).stream()
                .map(this::unwrapRow)
                .toList();

        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();
        Set<String> nodeIds = new HashSet<>();
        Set<String> edgeIds = new HashSet<>();

        getPublicacionById(id).ifPresent(publicacion ->
                addNode(nodes, nodeIds, "publicacion-" + publicacion.get("id"), String.valueOf(publicacion.get("texto")), "publicacion")
        );

        String anteriorUsuarioId = null;
        for (Map<String, Object> row : rawData) {
            Map<String, Object> usuario = extractMap(row.get("usuario"));

            if (usuario != null) {
                String usuarioNodeId = "usuario-" + usuario.get("id");
                addNode(nodes, nodeIds, usuarioNodeId, String.valueOf(usuario.get("nombre")), "usuario");
                addEdge(edges, edgeIds, usuarioNodeId, "publicacion-" + id, "compartio");

                if (anteriorUsuarioId != null) {
                    addEdge(edges, edgeIds, anteriorUsuarioId, usuarioNodeId, "propaga");
                }
                anteriorUsuarioId = usuarioNodeId;
            }
        }

        Map<String, Object> grafo = new LinkedHashMap<>();
        grafo.put("nodes", nodes);
        grafo.put("edges", edges);
        return grafo;
    }

    // Devuelve el usuario que actua como origen o paciente cero de la publicacion.
    public Optional<Map<String, Object>> getOrigenPublicacion(Long id) {
        return publicacionRepository.findOrigenMapByPublicacionId(id).stream()
                .findFirst()
                .map(this::unwrapRow);
    }

    private void addNode(List<Map<String, Object>> nodes, Set<String> nodeIds, String id, String label, String group) {
        if (id == null || !nodeIds.add(id)) {
            return;
        }

        Map<String, Object> node = new LinkedHashMap<>();
        node.put("id", id);
        node.put("label", label);
        node.put("group", group);
        nodes.add(node);
    }

    private void addEdge(List<Map<String, Object>> edges, Set<String> edgeIds, String from, String to, String label) {
        String edgeId = from + "->" + to + ":" + label;
        if (!edgeIds.add(edgeId)) {
            return;
        }

        Map<String, Object> edge = new LinkedHashMap<>();
        edge.put("from", from);
        edge.put("to", to);
        edge.put("label", label);
        edges.add(edge);
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
}
