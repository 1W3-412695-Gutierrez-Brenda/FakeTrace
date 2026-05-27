package com.faketrace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Node("Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    // Identificador interno generado por Neo4j.
    @Id
    @GeneratedValue
    private Long id;

    // Nombre visible del usuario.
    private String nombre;

    // Cantidad de seguidores del usuario.
    private Integer cantSeguidores;

    // Indica si el usuario esta verificado.
    private Boolean verificado;

    // Fecha de registro del usuario.
    private LocalDate fechaRegistro;

    // Publicaciones compartidas por el usuario.
    @Relationship(type = "COMPARTIO", direction = Relationship.Direction.OUTGOING)
    private List<Compartio> publicacionesCompartidas = new ArrayList<>();

    // Usuarios a los que este usuario sigue.
    @Relationship(type = "SIGUE", direction = Relationship.Direction.OUTGOING)
    private List<Usuario> usuariosSeguidos = new ArrayList<>();
}
