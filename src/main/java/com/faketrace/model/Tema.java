package com.faketrace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Tema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tema {

    // Identificador interno generado por Neo4j.
    @Id
    @GeneratedValue
    private Long id;

    // Nombre del tema.
    private String nombre;

    // Categoria general del tema.
    private String categoria;
}
