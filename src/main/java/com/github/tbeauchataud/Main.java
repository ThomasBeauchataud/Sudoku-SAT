package com.github.tbeauchataud;

import org.sat4j.specs.ISolver;
import stev.booleans.*;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 * Utilise la version 1.1 de la librairie booleans
 */
public class Main {

    private static final SudokuConstraintsFormatterInterface sudokuConstraintsFormatter = new SudokuConstraintsFormatter();
    private static final SudokuGridFormatterInterface sudokuGridFormatter = new SudokuGridFormatter();
    private static final SolverFactoryInterface solverFactory = new com.github.tbeauchataud.SolverFactory();
    private static final SudokuModelDecoderInterface sudokuModelDecoder = new SudokuModelDecoder();

    /**
     * Le nom des variables propositionnelles sont des entiers contenant :
     * le numero de la colonne de la cellule
     * le numero de la ligne de la celle
     * la valeure de la celle
     * example : la variable 452 correspondrait a "La cellule ligne 4 colonne 5 a pour valeur 2"
     *
     * @param args String[] args[0] doit contenir le sudoku encodé
     */
    public static void main(String[] args) {
        try {
            System.out.println("Looking for if the sudoku " + args[0] + " is satisfiable");
            int gridSize = 9;
            BooleanFormula formula = sudokuGridFormatter.formatGridToFormula(args[0], '#', gridSize);
            formula = sudokuConstraintsFormatter.formatCellConstraints(formula, gridSize);
            formula = sudokuConstraintsFormatter.formatColumnConstraints(formula, gridSize);
            formula = sudokuConstraintsFormatter.formatRowConstraints(formula, gridSize);
            formula = sudokuConstraintsFormatter.formatSquareConstraints(formula, gridSize);
            printCnf(formula);
            ISolver solver = solverFactory.createSolverFromCnf(formula.toCnf());
            if (solver.isSatisfiable()) {
                System.out.println("This sudoku is satisfiable");
                System.out.println();
                sudokuModelDecoder.decode(solver.model(), formula);
            } else {
                System.out.print("This sudoku is not satisfiable");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the Cnf BooleanFormula
     *
     * @param formula BooleanFormula the cnf formula
     */
    private static void printCnf(BooleanFormula formula) {
        System.out.println();
        System.out.println();
        System.out.println("CNF formula");
        System.out.println(formula.toCnf());
        System.out.println();
    }
}
