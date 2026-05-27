package com.faketrace.repository;

import com.faketrace.model.Tema;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TemaRepository extends Neo4jRepository<Tema, Long> {

    // Cantidad de publicaciones falsas agrupadas por tema.
    @Query("MATCH (t:Tema)<-[:TRATA_SOBRE]-(p:Publicacion {esVerificada: false}) " +
           "WITH t, count(p) AS cantidadPublicacionesFalsas " +
           "RETURN {tema: {id: t.id, nombre: t.nombre, categoria: t.categoria}, cantidadPublicacionesFalsas: cantidadPublicacionesFalsas} AS row " +
           "ORDER BY cantidadPublicacionesFalsas DESC")
    List<Map<String, Object>> findCantidadPublicacionesFalsasPorTema();
}
