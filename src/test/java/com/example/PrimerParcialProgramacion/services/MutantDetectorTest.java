package com.example.PrimerParcialProgramacion.services;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MutantDetectorTest {

    @Test
    public void testMutantDnaCase1() {
        List<String> mutantDna = List.of(
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTG"
        );

        boolean result = MutantDetector.isMutant(mutantDna);
        assertTrue(result, "Should return true for mutant DNA");
    }

    @Test
    public void testNonMutantDnaCase1() {
        List<String> nonMutantDna = List.of(
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        );

        boolean result = MutantDetector.isMutant(nonMutantDna);
        assertFalse(result, "Should return false for non-mutant DNA");
    }

    @Test
    public void testMutantDnaCase2() {
        List<String> mutantDna = List.of(
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        );

        boolean result = MutantDetector.isMutant(mutantDna);
        assertTrue(result, "Should return true for mutant DNA");
    }



    @Test
    public void testNonMutantDnaCase3() {
        List<String> nonMutantDna = List.of(
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        );

        boolean result = MutantDetector.isMutant(nonMutantDna);
        assertFalse(result, "Should return false for non-mutant DNA");
    }
    @Test
    public void testMutantDnaCase4() {
        List<String> mutantDna = List.of(
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTAG",
                "AAATGTACG",
                "ACGAGTGCT",
                "AGACATGTG",
                "GAACTCAAA",
                "ACTACGACC",
                "TGAGTATCC"
        );

        boolean result = MutantDetector.isMutant(mutantDna);
        assertTrue(result, "Should return true for mutant DNA (case 1).");
    }

    @Test
    public void testMutantDnaCase5() {
        List<String> mutantDna = List.of(
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCACT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCAT",
                "GCAGTCCCC"
        );

        boolean result = MutantDetector.isMutant(mutantDna);
        assertTrue(result, "Should return true for mutant DNA (case 2).");
    }
}
