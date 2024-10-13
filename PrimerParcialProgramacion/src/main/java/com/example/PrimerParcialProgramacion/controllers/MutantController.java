package com.example.PrimerParcialProgramacion.controllers;


import com.example.PrimerParcialProgramacion.entities.Dna;
import com.example.PrimerParcialProgramacion.services.DnaService;
import com.example.PrimerParcialProgramacion.services.MutantDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant/")
public class MutantController {
    private DnaService dnaService;

    public MutantController(DnaService dnaService){
        this.dnaService = dnaService;
    }



    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Dna dna) {
        try {
            // Verificar si el ADN es de un mutante
            boolean isMutant = MutantDetector.isMutant(dna.getDnaSequence());

            if (isMutant) {
                // Guardar el ADN mutante
                return ResponseEntity.status(HttpStatus.OK).body(dnaService.save(dna));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"No es un mutante\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

}