package com.faketrace.repository;

import com.faketrace.model.Publicacion;
import com.faketrace.model.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PublicacionRepository extends Neo4jRepository<Publicacion, Long> {

    // Top 3 publicaciones mas compartidas en formato simple para el dashboard.
    @Query("MATCH (p:Publicacion) " +
           "OPTIONAL MATCH (p)-[:ORIGINADA_EN]->(f:Fuente) " +
           "OPTIONAL MATCH (p)-[:TRATA_SOBRE]->(t:Tema) " +
           "RETURN {id: p.id, texto: p.texto, fechaCreacion: p.fechaCreacion, esVerificada: p.esVerificada, cantCompartidos: p.cantCompartidos, " +
           "fuente: CASE WHEN f IS NULL THEN null ELSE {id: f.id, nombre: f.nombre, url: f.url, confiable: f.confiable} END, " +
           "tema: CASE WHEN t IS NULL THEN null ELSE {id: t.id, nombre: t.nombre, categoria: t.categoria} END} AS row " +
           "ORDER BY p.cantCompartidos DESC " +
           "LIMIT 3")
    List<Map<String, Object>> findTop3PublicacionesMasCompartidas();

    // Detalle de una publicacion por su propiedad id, con fuente y tema.
    @Query("MATCH (p:Publicacion {id: $publicacionId}) " +
           "OPTIONAL MATCH (p)-[:ORIGINADA_EN]->(f:Fuente) " +
           "OPTIONAL MATCH (p)-[:TRATA_SOBRE]->(t:Tema) " +
           "RETURN {id: p.id, texto: p.texto, fechaCreacion: p.fechaCreacion, esVerificada: p.esVerificada, cantCompartidos: p.cantCompartidos, " +
           "fuente: CASE WHEN f IS NULL THEN null ELSE {id: f.id, nombre: f.nombre, url: f.url, confiable: f.confiable} END, " +
           "tema: CASE WHEN t IS NULL THEN null ELSE {id: t.id, nombre: t.nombre, categoria: t.categoria} END} AS row")
    List<Map<String, Object>> findDetalleByPublicacionId(@Param("publicacionId") Long publicacionId);

    // Origen de una publicacion por su propiedad id, devuelto como mapa simple.
    @Query("MATCH (u:Usuario)-[c:COMPARTIO]->(p:Publicacion {id: $publicacionId}) " +
           "RETURN {id: u.id, nombre: u.nombre, cantSeguidores: u.cantSeguidores, verificado: u.verificado, fechaRegistro: u.fechaRegistro} AS row " +
           "ORDER BY c.fecha ASC, c.hora ASC " +
           "LIMIT 1")
    List<Map<String, Object>> findOrigenMapByPublicacionId(@Param("publicacionId") Long publicacionId);

    // Usuarios que compartieron una publicacion, ordenados como cadena temporal.
    @Query("MATCH (u:Usuario)-[c:COMPARTIO]->(p:Publicacion {id: $publicacionId}) " +
           "RETURN {usuario: {id: u.id, nombre: u.nombre, cantSeguidores: u.cantSeguidores, verificado: u.verificado, fechaRegistro: u.fechaRegistro}, fecha: c.fecha, hora: c.hora, plataforma: c.plataforma} AS row " +
           "ORDER BY c.fecha ASC, c.hora ASC")
    List<Map<String, Object>> findCadenaTemporalByPublicacionId(@Param("publicacionId") Long publicacionId);

    // Usuario origen de una publicacion, tomado como el primer usuario que la compartio.
    @Query("MATCH (u:Usuario)-[c:COMPARTIO]->(p:Publicacion {id: $publicacionId}) " +
           "RETURN u " +
           "ORDER BY c.fecha ASC, c.hora ASC " +
           "LIMIT 1")
    List<Usuario> findOrigenByPublicacionId(@Param("publicacionId") Long publicacionId);

    // Cadena completa de propagacion inferida por relaciones SIGUE hasta 10 saltos.
    @Query("MATCH (origen:Usuario)-[c:COMPARTIO]->(p:Publicacion {id: $publicacionId}) " +
           "WITH p, origen, c " +
           "ORDER BY c.fecha ASC, c.hora ASC " +
           "LIMIT 1 " +
           "MATCH path = (origen)<-[:SIGUE*0..10]-(usuario:Usuario) " +
           "WHERE (usuario)-[:COMPARTIO]->(p) " +
           "RETURN usuario AS usuario, length(path) AS saltos, nodes(path) AS camino " +
           "ORDER BY saltos DESC")
    List<Map<String, Object>> findCadenaPropagacionByPublicacionId(@Param("publicacionId") Long publicacionId);

    // Publicaciones falsas compartidas por mas de la cantidad de usuarios indicada.
    @Query("MATCH (p:Publicacion {esVerificada: false})<-[:COMPARTIO]-(u:Usuario) " +
           "WITH p, count(DISTINCT u) AS totalUsuarios " +
           "WHERE totalUsuarios > $cantidadUsuarios " +
           "RETURN p " +
           "ORDER BY totalUsuarios DESC")
    List<Publicacion> findPublicacionesFalsasConMasDeNUsuarios(@Param("cantidadUsuarios") Long cantidadUsuarios);

    // Comparacion del promedio de saltos de propagacion entre publicaciones falsas y verificadas.
    @Query("MATCH (origen:Usuario)-[c:COMPARTIO]->(p:Publicacion) " +
           "WITH p, origen, c " +
           "ORDER BY c.fecha ASC, c.hora ASC " +
           "WITH p, collect(origen)[0] AS origen " +
           "MATCH path = (origen)<-[:SIGUE*0..10]-(usuario:Usuario) " +
           "WHERE (usuario)-[:COMPARTIO]->(p) " +
           "RETURN p.esVerificada AS esVerificada, avg(length(path)) AS promedioSaltos, count(DISTINCT usuario) AS usuariosAlcanzados " +
           "ORDER BY esVerificada ASC")
    List<Map<String, Object>> compararSaltosPromedioFalsasVsVerificadas();
}
