package com.faketrace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node("Publicacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {

    // Identificador interno generado por Neo4j.
    @Id
    @GeneratedValue
    private Long id;

    // Texto principal de la publicacion.
    private String texto;

    // Fecha y hora de creacion de la publicacion.
    private LocalDateTime fechaCreacion;

    // Indica si la publicacion fue verificada.
    private Boolean esVerificada;

    // Cantidad total de veces que fue compartida.
    private Integer cantCompartidos;

    // Fuente donde se origino la publicacion.
    @Relationship(type = "ORIGINADA_EN", direction = Relationship.Direction.OUTGOING)
    private Fuente fuente;

    // Tema principal tratado por la publicacion.
    @Relationship(type = "TRATA_SOBRE", direction = Relationship.Direction.OUTGOING)
    private Tema tema;
}
