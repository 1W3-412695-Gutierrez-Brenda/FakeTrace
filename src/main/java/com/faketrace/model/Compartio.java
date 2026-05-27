package com.faketrace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDate;
import java.time.LocalTime;

@RelationshipProperties
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compartio {

    // Identificador interno generado para la relacion.
    @Id
    @GeneratedValue
    private Long id;

    // Publicacion compartida por el usuario.
    @TargetNode
    private Publicacion publicacion;

    // Fecha en la que se compartio la publicacion.
    private LocalDate fecha;

    // Hora en la que se compartio la publicacion.
    private LocalTime hora;

    // Plataforma desde la que se compartio.
    private String plataforma;
}
