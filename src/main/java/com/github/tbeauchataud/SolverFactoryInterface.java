package com.github.tbeauchataud;

import org.sat4j.specs.ISolver;
import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface SolverFactoryInterface {

    /**
     * Return a solver created with a BooleanFormulation in a Cnf format
     *
     * @param formula BooleanFormulation the formula in Cnf containing clauses
     * @return ISolver
     */
    ISolver createSolverFromCnf(BooleanFormula formula);

}
