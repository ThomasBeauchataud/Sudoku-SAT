package com.github.tbeauchataud;

import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface SudokuModelDecoderInterface {

    /**
     * Decode a solver model with his BooleanFormula
     *
     * @param model   int[] the model of the solver
     * @param formula BooleanFormula the BooleanFormula given to the solver
     */
    void decode(int[] model, BooleanFormula formula);

}
