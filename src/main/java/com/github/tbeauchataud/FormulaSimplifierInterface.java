package com.github.tbeauchataud;

import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface FormulaSimplifierInterface {

    /**
     * Simplify The formula to dodge stack overflow Exception
     *
     * @param formula BooleanFormula the formula to simplify
     * @return BooleanFormula
     */
    BooleanFormula simplify(BooleanFormula formula);

}
