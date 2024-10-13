package com.example.PrimerParcialProgramacion.controllers;

/*import com.example.PrimerParcialProgramacion.services.MutantDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;*/
import com.example.PrimerParcialProgramacion.entities.Dna;
import com.example.PrimerParcialProgramacion.services.DnaService;
import com.example.PrimerParcialProgramacion.services.MutantDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*@RequestMapping("/api/mutants")
public class MutantController {

    private final MutantDetector mutantDetector;

    public MutantController(MutantDetector mutantDetector) {
        this.mutantDetector = mutantDetector;
    }

    @PostMapping("")
    public ResponseEntity<?> detectMutant(@RequestBody String[] dna) {
        boolean isMutant = mutantDetector.isMutant(dna);

        if (isMutant) {
            return ResponseEntity.status(HttpStatus.OK).build(); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
    }
}*/
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant/")
public class MutantController {
    private DnaService dnaService;

    public MutantController(DnaService dnaService){
        this.dnaService = dnaService;
    }


    /*
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }
*/
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
/*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody Dna entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.update(id , entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delate(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dnaService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }
*/
}