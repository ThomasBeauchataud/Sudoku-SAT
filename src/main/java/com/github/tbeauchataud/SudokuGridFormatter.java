package com.github.tbeauchataud;

import stev.booleans.And;
import stev.booleans.BooleanFormula;
import stev.booleans.Or;
import stev.booleans.PropositionalVariable;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public class SudokuGridFormatter implements SudokuGridFormatterInterface {

    private final FormulaSimplifierInterface formulaSimplifier = new FormulaSimplifier();

    /**
     * Genere une BooleanFormula a partir d'un sudoku encodé en chaine de character
     * Pour chaque character c de la chaine de character, soit i la ligne de la cellule et k la colonne de la cellule
     * Si c n'est pas la valeur par default (c != #) alors ikc = Vrai
     * example : i = 2, k = 5, c = 6, alors 256 = Vrai
     * Si c est la valeur par default (c = #) alors la somme de OR entre les differentes valeur possible est vrai
     * example : i = 2, k = 5, c = #, alors (251 | 252 | 253 | 254 | 255 | 256 | 257 | 258 | 259) = Vrai
     *
     * @param grid         String la chaine de character
     * @param defaultValue char le character encodant une valeur inconnue
     * @param gridSize     int la taille du sudoku
     * @return BooleanFormula
     */
    @Override
    public BooleanFormula formatGridToFormula(String grid, char defaultValue, int gridSize) {
        BooleanFormula formula = null;
        BooleanFormula tempVariable = null;
        for (int i = 0; i < grid.length(); ++i) {
            char c = grid.charAt(i);
            final int row = i / 9 + 1;
            final int column = (i - (9 * (row - 1))) + 1;
            if (c != defaultValue) {
                final String variableName = row + String.valueOf(column) + c;
                final PropositionalVariable variable = new PropositionalVariable(variableName);
                if (formula == null) {
                    if (tempVariable == null) {
                        tempVariable = variable;
                    } else {
                        formula = new And(tempVariable, variable);
                    }
                } else {
                    formula = new And(formula, variable);
                }
            } else {
                BooleanFormula subFormula = null;
                PropositionalVariable tempSubVariable = null;
                for (int k = 1; k <= gridSize; ++k) {
                    final String variableName = row + String.valueOf(column) + k;
                    final PropositionalVariable variable = new PropositionalVariable(variableName);
                    if (subFormula == null) {
                        if (tempSubVariable == null) {
                            tempSubVariable = variable;
                        } else {
                            subFormula = new Or(tempSubVariable, variable);
                        }
                    } else {
                        subFormula = new Or(subFormula, variable);
                    }
                }
                if (formula == null) {
                    if (tempVariable == null) {
                        tempVariable = subFormula;
                    } else {
                        formula = new And(tempVariable, subFormula);
                    }
                } else {
                    formula = new And(formula, subFormula);
                }
            }
        }
        return formulaSimplifier.simplify(formula);
    }

}
