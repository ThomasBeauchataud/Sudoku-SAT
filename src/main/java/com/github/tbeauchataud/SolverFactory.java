package com.github.tbeauchataud;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import stev.booleans.BooleanFormula;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public class SolverFactory implements SolverFactoryInterface {

    private static final int MAX_VAR = 1000000;

    /**
     * Return a solver created with a BooleanFormulation in a Cnf format
     *
     * @param formula BooleanFormulation the formula in Cnf containing clauses
     * @return ISolver
     */
    public ISolver createSolverFromCnf(BooleanFormula formula) {
        int[][] clauses = formula.getClauses();
        final int NB_CLAUSES = clauses.length;
        ISolver solver = org.sat4j.minisat.SolverFactory.newDefault();
        solver.newVar(MAX_VAR);
        solver.setExpectedNumberOfClauses(NB_CLAUSES);
        ContradictionException exception = null;
        for (int[] clause : clauses) {
            try {
                VecInt v = new VecInt(clause);
                solver.addClause(v);
            } catch (ContradictionException e) {
                if (exception == null) {
                    e.printStackTrace();
                    exception = e;
                }
                if (!e.getMessage().equals(exception.getMessage())) {
                    e.printStackTrace();
                }
            }
        }
        return solver;
    }

}
