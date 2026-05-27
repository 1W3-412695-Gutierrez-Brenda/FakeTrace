package com.faketrace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Fuente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fuente {

    // Identificador interno generado por Neo4j.
    @Id
    @GeneratedValue
    private Long id;

    // Nombre de la fuente de informacion.
    private String nombre;

    // URL asociada a la fuente.
    private String url;

    // Indica si la fuente es considerada confiable.
    private Boolean confiable;
}
