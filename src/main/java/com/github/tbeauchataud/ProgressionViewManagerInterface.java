package com.github.tbeauchataud;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public interface ProgressionViewManagerInterface {

    /**
     * Print the progression of the function in the console
     *
     * @param progression  int the progression in percentage
     * @param functionName String the name of the function
     */
    void printProgression(int progression, String functionName);

}
