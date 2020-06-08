package com.github.tbeauchataud;

import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface SudokuGridFormatterInterface {

    /**
     * Genere une BooleanFormula a partir d'un sudoku encodé en chaine de character
     *
     * @param grid         String la chaine de character
     * @param defaultValue char le character encodant une valeur inconnue
     * @param gridSize     int la taille du sudoku
     * @return BooleanFormula
     */
    BooleanFormula formatGridToFormula(String grid, char defaultValue, int gridSize);

}
