package com.example.PrimerParcialProgramacion.controllers;

import com.example.PrimerParcialProgramacion.entities.Dna;
import com.example.PrimerParcialProgramacion.services.DnaService;
import com.example.PrimerParcialProgramacion.services.MutantDetector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MutantControllerTest {

    @Mock
    private DnaService dnaService;

    @InjectMocks
    private MutantController mutantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMutant() throws Exception {
        // Preparar datos de prueba
        Dna dna = new Dna();
        List<String> dnaList = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        dna.setDnaSequence(dnaList);

        // Mockear el método estático MutantDetector.isMutant()
        try (MockedStatic<MutantDetector> mockedDetector = mockStatic(MutantDetector.class)) {
            mockedDetector.when(() -> MutantDetector.isMutant(dnaList)).thenReturn(true);

            // Simular el guardado del ADN
            when(dnaService.save(any(Dna.class))).thenReturn(dna);

            // Ejecutar el método
            ResponseEntity<?> response = mutantController.save(dna);

            // Verificar el resultado
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(dna, response.getBody());
        }
    }

    @Test
    void testSaveNonMutant() throws Exception {
        // Preparar datos de prueba
        Dna dna = new Dna();
        List<String> dnaList = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        dna.setDnaSequence(dnaList);

        // Mockear el método estático MutantDetector.isMutant()
        try (MockedStatic<MutantDetector> mockedDetector = mockStatic(MutantDetector.class)) {
            mockedDetector.when(() -> MutantDetector.isMutant(dnaList)).thenReturn(false);

            // Ejecutar el método
            ResponseEntity<?> response = mutantController.save(dna);

            // Verificar el resultado
            assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
            assertEquals("{\"error\":\"No es un mutante\"}", response.getBody());
        }
    }

    @Test
    void testSaveError() throws Exception {
        // Preparar datos de prueba
        Dna dna = new Dna();
        dna.setDnaSequence(null); // Dato que causará error

        // Simular que ocurre una excepción
        when(dnaService.save(any(Dna.class))).thenThrow(new RuntimeException("Error al guardar"));

        // Ejecutar el método
        ResponseEntity<?> response = mutantController.save(dna);

        // Verificar el resultado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"Error\"}", response.getBody());
    }
}
