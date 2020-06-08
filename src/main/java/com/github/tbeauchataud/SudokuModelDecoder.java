package com.github.tbeauchataud;

import stev.booleans.BooleanFormula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public class SudokuModelDecoder implements SudokuModelDecoderInterface {

    /**
     * Decode a solver model with his BooleanFormula
     *
     * @param model   int[] the model of the solver
     * @param formula BooleanFormula the BooleanFormula given to the solver
     */
    @Override
    public void decode(int[] model, BooleanFormula formula) {
        Map<String, Integer> variables = formula.getVariablesMap();
        List<Integer> validValues = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            for (int v : model) {
                if (entry.getValue() == v && v > 0) {
                    validValues.add(Integer.parseInt(entry.getKey()));
                }
            }
        }
        Collections.sort(validValues);
        System.out.println("Resolved sudoku : ");
        for (int v : validValues) {
            while(v > 100) {
                v -= 100;
            }
            while(v > 10) {
                v -= 10;
            }
            System.out.print(v);
        }
        System.out.println();
    }

}
