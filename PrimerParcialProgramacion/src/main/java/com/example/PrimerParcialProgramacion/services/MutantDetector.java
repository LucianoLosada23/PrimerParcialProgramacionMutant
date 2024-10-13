package com.example.PrimerParcialProgramacion.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantDetector {
    private static final int MIN_SEQUENCE_LENGTH = 4;
    private static final char[] BASES = {'A', 'T', 'C', 'G'};

    public static boolean isMutant(List<String> dna) {
        int n = dna.size();
        int count = 0;

        if (n < MIN_SEQUENCE_LENGTH || dna.get(0).length() < MIN_SEQUENCE_LENGTH) {
            return false;
        }

        // Verificar filas y columnas
        for (int i = 0; i < n; i++) {
            StringBuilder column = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j <= n - MIN_SEQUENCE_LENGTH) {
                    count += checkSequence(dna.get(i).substring(j, j + MIN_SEQUENCE_LENGTH)) ? 1 : 0;
                }
                column.append(dna.get(j).charAt(i));
            }

            for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
                count += checkSequence(column.substring(j, j + MIN_SEQUENCE_LENGTH)) ? 1 : 0;
            }
        }

        // Verificar diagonales
        for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
                if (checkDiagonal(dna, i, j, 1)) {
                    count++;
                }
                if (checkDiagonal(dna, i, j + MIN_SEQUENCE_LENGTH - 1, -1)) {
                    count++;
                }
            }
        }

        return count > 1;
    }

    private static boolean checkSequence(String sequence) {
        for (char base : BASES) {
            String repeated = String.valueOf(base).repeat(MIN_SEQUENCE_LENGTH);
            if (sequence.equals(repeated)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonal(List<String> dna, int row, int col, int direction) {
        StringBuilder diagonal = new StringBuilder();
        for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
            diagonal.append(dna.get(row + k).charAt(col + k * direction));
        }
        return checkSequence(diagonal.toString());
    }
}
