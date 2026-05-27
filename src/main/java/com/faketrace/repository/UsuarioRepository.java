package com.faketrace.repository;

import com.faketrace.model.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long> {

    // Top 10 usuarios que mas publicaciones compartieron en formato simple para ranking.
    @Query("MATCH (u:Usuario)-[:COMPARTIO]->(p:Publicacion) " +
           "WITH u, count(p) AS cantidadCompartidos " +
           "RETURN {usuario: {id: u.id, nombre: u.nombre, cantSeguidores: u.cantSeguidores, verificado: u.verificado, fechaRegistro: u.fechaRegistro}, cantidadCompartidos: cantidadCompartidos} AS row " +
           "ORDER BY cantidadCompartidos DESC " +
           "LIMIT 10")
    List<Map<String, Object>> findTop10SuperpropagadoresConCantidad();

    // Top 5 usuarios que mas publicaciones compartieron.
    @Query("MATCH (u:Usuario)-[:COMPARTIO]->(p:Publicacion) " +
           "RETURN u " +
           "ORDER BY count(p) DESC " +
           "LIMIT 5")
    List<Usuario> findTop5Superpropagadores();

    // Usuarios que compartieron la misma publicacion con menos de 60 segundos de diferencia.
    @Query("MATCH (u1:Usuario)-[c1:COMPARTIO]->(p:Publicacion)<-[c2:COMPARTIO]-(u2:Usuario) " +
           "WHERE id(u1) < id(u2) " +
           "AND c1.fecha = c2.fecha " +
           "AND abs(duration.inSeconds(datetime(c1.fecha + 'T' + c1.hora), datetime(c2.fecha + 'T' + c2.hora)).seconds) < 60 " +
           "RETURN DISTINCT {usuario: {id: u1.id, nombre: u1.nombre, cantSeguidores: u1.cantSeguidores, verificado: u1.verificado, fechaRegistro: u1.fechaRegistro}, publicacion: {id: p.id, texto: p.texto}, fecha: c1.fecha, hora: c1.hora, horaComparada: c2.hora} AS row " +
           "UNION " +
           "MATCH (u1:Usuario)-[c1:COMPARTIO]->(p:Publicacion)<-[c2:COMPARTIO]-(u2:Usuario) " +
           "WHERE id(u1) < id(u2) " +
           "AND c1.fecha = c2.fecha " +
           "AND abs(duration.inSeconds(datetime(c1.fecha + 'T' + c1.hora), datetime(c2.fecha + 'T' + c2.hora)).seconds) < 60 " +
           "RETURN DISTINCT {usuario: {id: u2.id, nombre: u2.nombre, cantSeguidores: u2.cantSeguidores, verificado: u2.verificado, fechaRegistro: u2.fechaRegistro}, publicacion: {id: p.id, texto: p.texto}, fecha: c2.fecha, hora: c2.hora, horaComparada: c1.hora} AS row")
    List<Map<String, Object>> findUsuariosConComportamientoBot();

    // Devuelve las relaciones SIGUE en formato simple para calcular caminos.
    @Query("MATCH (origen:Usuario)-[:SIGUE]->(destino:Usuario) " +
           "RETURN {origen: {id: origen.id, nombre: origen.nombre, cantSeguidores: origen.cantSeguidores, verificado: origen.verificado, fechaRegistro: origen.fechaRegistro}, " +
           "destino: {id: destino.id, nombre: destino.nombre, cantSeguidores: destino.cantSeguidores, verificado: destino.verificado, fechaRegistro: destino.fechaRegistro}} AS row")
    List<Map<String, Object>> findRelacionesSigue();

    // Usuarios que siguen a un superpropagador y tambien compartieron alguna de sus publicaciones.
    @Query("MATCH (super:Usuario)-[:COMPARTIO]->(p:Publicacion) " +
           "WITH super, count(p) AS totalCompartidos " +
           "ORDER BY totalCompartidos DESC " +
           "LIMIT 5 " +
           "MATCH (seguidor:Usuario)-[:SIGUE]->(super) " +
           "MATCH (super)-[:COMPARTIO]->(publicacion:Publicacion)<-[:COMPARTIO]-(seguidor) " +
           "RETURN DISTINCT seguidor")
    List<Usuario> findSeguidoresQueCompartieronPublicacionesDeSuperpropagadores();
}
