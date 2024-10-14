package com.example.PrimerParcialProgramacion.repositories;

import com.example.PrimerParcialProgramacion.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<Dna,Long> {

}
