package com.github.tbeauchataud;

import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface SudokuConstraintsFormatterInterface {

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par lignes (valeur differentes)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    BooleanFormula formatRowConstraints(BooleanFormula formula, int gridSize);

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par colonnes (valeur differentes)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    BooleanFormula formatSquareConstraints(BooleanFormula formula, int gridSize);

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par carrés (valeur differentes)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    BooleanFormula formatColumnConstraints(BooleanFormula formula, int gridSize);

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par cellules (une valeur par cellule)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    BooleanFormula formatCellConstraints(BooleanFormula formula, int gridSize);

}
