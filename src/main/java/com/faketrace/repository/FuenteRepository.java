package com.faketrace.repository;

import com.faketrace.model.Fuente;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FuenteRepository extends Neo4jRepository<Fuente, Long> {

    // Fuentes ordenadas por la cantidad de publicaciones falsas que originaron.
    @Query("MATCH (f:Fuente)<-[:ORIGINADA_EN]-(p:Publicacion {esVerificada: false}) " +
           "WITH f, count(p) AS cantidadPublicacionesFalsas " +
           "RETURN {fuente: {id: f.id, nombre: f.nombre, url: f.url, confiable: f.confiable}, cantidadPublicacionesFalsas: cantidadPublicacionesFalsas} AS row " +
           "ORDER BY cantidadPublicacionesFalsas DESC")
    List<Map<String, Object>> findFuentesOrdenadasPorPublicacionesFalsas();
}
