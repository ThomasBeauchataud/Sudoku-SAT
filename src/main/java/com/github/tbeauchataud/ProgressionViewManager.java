package com.github.tbeauchataud;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Thomas Beauchataud BEAT21029603
 * @since 04.06.2020
 */
public class ProgressionViewManager implements ProgressionViewManagerInterface {

    private static Map<String, Integer> progressions = new HashMap<>();

    /**
     * Print the progression of the function in the console
     *
     * @param progression  int the progression in percentage
     * @param functionName String the name of the function
     */
    @Override
    public void printProgression(int progression, String functionName) {
        final int c = progression / 4;
        if (!progressions.containsKey(functionName)) {
            progressions.put(functionName, 0);
            System.out.println();
        }
        int pr = progressions.get(functionName);
        if (c != pr) {
            StringBuilder stringBuilder = new StringBuilder("\r[");
            for (int p = 0; p < c; ++p) {
                stringBuilder.append('=');
            }
            stringBuilder.append('>');
            for (int p = c; p < 25; ++p) {
                stringBuilder.append(' ');
            }
            stringBuilder.append("]   -   ").append(functionName);
            System.out.print(stringBuilder.toString());
        }
        progressions.put(functionName, c);
    }

}
