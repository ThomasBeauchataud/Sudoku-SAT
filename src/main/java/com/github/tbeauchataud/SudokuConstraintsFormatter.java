package com.github.tbeauchataud;

import stev.booleans.*;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public class SudokuConstraintsFormatter implements SudokuConstraintsFormatterInterface {

    private final FormulaSimplifierInterface formulaSimplifier = new FormulaSimplifier();
    private final ProgressionViewManagerInterface progressionViewManager = new ProgressionViewManager();

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par lignes (valeur differentes)
     * Pour chaque cellule c1 ayant une valeur x
     * Pour chaque cellule c2 de la meme ligne que c1
     * c1(x) -> !c2(x)
     * example : 111 -> (!121 & !131 & !141 & !151 & !161 & !171 & !181 & !191)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    @Override
    public BooleanFormula formatRowConstraints(BooleanFormula formula, int gridSize) {
        for (int i = 111; i <= (111 * gridSize); ++i) {
            if (i % 10 != 0) {
                for (int k = i; (k / 100) < 10; k += 100) {
                    if (k != i) {
                        formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(k)));
                    }
                }
                for (int k = i; (k / 110) > 0; k -= 100) {
                    if (k != i) {
                        formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(k)));
                    }
                }
                this.progressionViewManager.printProgression(i * 100 / (111 * gridSize), "formatRowConstraints");
                formula = formulaSimplifier.simplify(formula);
            }
        }

        return formula;
    }

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par colonnes (valeur differentes)
     * Pour chaque cellule c1 ayant une valeur x
     * Pour chaque cellule c2 de du meme carré que c1
     * c1(x) -> !c2(x)
     * example : 111 -> (!121 & !131 & !211 & !221 & !231 & !311 & !321 & !331)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    @Override
    public BooleanFormula formatSquareConstraints(BooleanFormula formula, int gridSize) {
        for (int i = 111; i <= (111 * gridSize); ++i) {
            if (i % 10 != 0) {
                for (int k = i - 200; k <= i + 200; k += 100) {
                    for (int j = k - 20; j <= k + 20; j += 10) {
                        boolean inRow = (i - 100) / 300 == (j - 100) / 300 && j > 111;
                        int t1 = j - ((j / 100) * 100);
                        int t2 = i - ((i / 100) * 100);
                        boolean inColumn = (t1 - 10) / 30 == (t2 - 10) / 30 && t1 >= 11;
                        if (inRow && inColumn) {
                            if (j != i) {
                                formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(j)));
                            }
                        }
                    }
                }
                this.progressionViewManager.printProgression(i * 100 / (111 * gridSize), "formatSquareConstraints");
                formula = formulaSimplifier.simplify(formula);
            }
        }
        return formula;
    }

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par colonnes (valeur differentes)
     * Pour chaque cellule c1 ayant une valeur x
     * Pour chaque cellule c2 de la meme colonne que c1
     * c1(x) -> !c2(x)
     * example : 111 -> (!211 & !311 & !411 & !511 & !611 & !711 & !811 & !911)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    @Override
    public BooleanFormula formatColumnConstraints(BooleanFormula formula, int gridSize) {
        for (int i = 111; i <= (111 * gridSize); ++i) {
            if (i % 10 != 0) {
                for (int k = i; (k / 100) == (i / 100) && (k - ((k / 100) * 100)) > 10; k -= 10) {
                    if (k != i) {
                        formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(k)));
                    }
                }
                for (int k = i; (k / 100) == (i / 100); k += 10) {
                    if (k != i) {
                        formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(k)));
                    }
                }
                this.progressionViewManager.printProgression(i * 100 / (111 * gridSize), "formatColumnConstraints");
                formula = formulaSimplifier.simplify(formula);
            }
        }
        return formula;
    }

    /**
     * Enrichie une BooleanFormula d'un sudoku avec les contraintes par cellules (une valeur par cellule)
     * Pour chaque cellule c1 ayant une valeur x, soit y appartenant a l'ensemble des valeurs possible de c1
     * si x =! y, alors c1(x) -> !c1(y)
     * example : 111 -> (!112 & !113 & !114 & !115 & !116 & !117 & !118 & !119)
     *
     * @param formula  BooleanFormula la BooleanFormula existante d'un sudoku
     * @param gridSize int la taille du sudoku
     * @return BooleanFormula
     */
    @Override
    public BooleanFormula formatCellConstraints(BooleanFormula formula, int gridSize) {
        for (int i = 111; i <= (111 * gridSize); ++i) {
            if (i % 10 != 0) {
                final int row = (i / 100);
                final int column = (i - (row * 100)) / 10;
                final int value = i - (row * 100) - (column * 10);
                for (int k = 1; k <= gridSize; ++k) {
                    if (k != value) {
                        final int p = row * 100 + column * 10 + k;
                        if (p != i) {
                            formula = new And(formula, this.impliesNot(String.valueOf(i), String.valueOf(p)));
                        }
                    }
                }
                this.progressionViewManager.printProgression(i * 100 / (111 * gridSize), "formatCellConstraints");
                formula = formulaSimplifier.simplify(formula);
            }
        }
        return formula;
    }

    private Implies impliesNot(String i, String k) {
        return new Implies(new PropositionalVariable(i), new Not(new PropositionalVariable(k)));
    }

}
