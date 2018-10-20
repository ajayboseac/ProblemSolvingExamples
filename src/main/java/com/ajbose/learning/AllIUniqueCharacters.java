package com.ajbose.learning;

import java.util.HashMap;

public class AllIUniqueCharacters {

    /**
     * This program will check if the given string has all unique characters or not.
     *
     * @param args
     */
    public static void main(String[] args) {

        String[] testStrings = new String[]{"Iamwiner", "Iaamwinner"};
        for (String string : testStrings) {
            printResult(checkUniqueNessUsingMap(string));
            printResult(checkUniquenessUsingArray(string));
        }
    }

    private static void printResult(boolean b) {
        if (b) {
            System.out.println("Unique!");
        } else {
            System.out.println("Not Unique!");
        }
    }

    private static boolean checkUniqueNessUsingMap(String string) {
        char[] chars = string.toCharArray();
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>(10);
        for (char a : chars) {
            if (countMap.get(a) != null) {
                return false;
            }
            countMap.put(a, 1);
        }
        return true;
    }

    public static boolean checkUniquenessUsingArray(String string) {
        char[] chars = string.toCharArray();
        boolean[] booleanArray = new boolean[128];
        for (char a : chars) {
            if (booleanArray[a]) {
                return false;
            } else {
                booleanArray[a] = true;
            }
        }
        return true;
    }
}
