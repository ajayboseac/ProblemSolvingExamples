package com.ajbose.learning.stringmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This program will check how many times does a small string 'S1' appears in large String 'S2'
 */
public class FindIfSubSequence {

    public static void main(String[] args) {
        String largeString = "ajayaqqqqqqqwqwqwqwqwqajyjyaajaya" +
                "asasasasassasasasajayasasasasasaajaysasasasaaaajayaklkslkdlsk" +
                "dlsdkaslfnjksdnfsmdfnmdnfjanfmasfnsjfnajdfhajdfnmdfnadmfhjdfdfbjfdnsdfmnfjshfjshn" +
                "fjsdnfjasdhfjsdhfjdhfjsdfhdfsdjfasdjfhasdjfhsjdfhasjfdhasjdfhjdsafhjsdfhjsdfhjashdfjasdhfk" +
                "ajsfdhjdshfjashdfjdshfajahsfjhsdjfhadjfhadsjfhsjdfhasjfhasjdfhjfahjfhajfhasfjahsfjahsfjahfjfhasj" +
                "fhasjdfhsasdadadsadsasdasdasddsadsadasdadasdasdadasdadsadadasdasdadasasasasajayyyyyyyyyyyyyyyyyyyyyyy" +
                "yyysasasasasasasasas";
        largeString = largeString+largeString+largeString+largeString+largeString;
        String shortString = "ajay";
        int occurrences = findOccurrences(largeString, shortString);
        long begin = System.currentTimeMillis();
        System.out.println(String.format("The string:%s appeared %d times in string: %s",
                shortString, occurrences, largeString));
        System.out.println(String.format("Time taken for First: %d",System.currentTimeMillis()-begin));
        occurrences = findOccurrencesOptimized(largeString, shortString);
        begin = System.currentTimeMillis();
        System.out.println(String.format("The string:%s appeared %d times in string: %s",
                shortString, occurrences, largeString));
        System.out.println(String.format("Time taken for Second: %d",System.currentTimeMillis()-begin));
    }

    private static int findOccurrencesOptimized(String largeString, String shortString) {
        int numOccurrences =0;
        int patternTable[] = new int[255];
        int textTable[] = new int[255];
        for(int i =0;i<shortString.length();i++){
            patternTable[shortString.charAt(i)]++;
        }
        for(int i=0;i<largeString.length();i++){
            textTable[largeString.charAt(i)]++;
            if(i-shortString.length()>=0){
                textTable[largeString.charAt(i-shortString.length())]--;
                if(compareTables(patternTable,textTable)){
                    numOccurrences++;
                }
            }
        }
        return numOccurrences;
    }

    private static boolean compareTables(int[] patternTable, int[] textTable) {
        return Arrays.equals(patternTable,textTable);
    }


    private static int findOccurrences(String largeString, String shortString) {
        int shortStringLength = shortString.length();
        int numOccurrences = 0;
        Map<Character, Integer> table = tabulateShortString(shortString, shortStringLength);
        for (int i = 0; i < largeString.length() - shortStringLength; ) {
            Map<Character, Integer> tableCopy = new HashMap<>(table);
            boolean matchFound = true;
            for (int j = i; j < i + shortStringLength; j++) {
                char currentChar = largeString.charAt(j);
                if (tableCopy.get(currentChar) == null) {
                    matchFound = false;
                    i = j + 1;
                    break;
                }
                else if (tableCopy.get(currentChar) == 0) {
                    matchFound = false;
                    i++;
                    break;
                }  else {
                    tableCopy.put(currentChar, tableCopy.get(currentChar) - 1);
                }
            }
            if (matchFound) {
                numOccurrences++;
                i++;
            }
        }
        return numOccurrences;
    }

    /**
     * Tabulate the short String
     *
     * @param shortString
     * @param shortStringLength
     * @return
     */
    private static Map<Character, Integer> tabulateShortString(String shortString, int shortStringLength) {
        Map<Character, Integer> table = new HashMap<>(shortStringLength);
        for (int i = 0; i < shortStringLength; i++) {
            char currentChar = shortString.charAt(i);
            Integer count = table.get(currentChar);
            if (count == null) {
                table.put(currentChar, 1);
            } else {
                table.put(currentChar, count + 1);
            }
        }
        return table;
    }
}
