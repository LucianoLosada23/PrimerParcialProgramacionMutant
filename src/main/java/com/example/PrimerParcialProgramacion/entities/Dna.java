package com.example.PrimerParcialProgramacion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ADN")
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Dna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection // Para almacenar una lista de elementos simples
    @CollectionTable(name = "ADN_sequences", joinColumns = @JoinColumn(name = "dna_id"))
    private List<String> dnaSequence;
}
