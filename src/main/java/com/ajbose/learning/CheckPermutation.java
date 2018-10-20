package com.ajbose.learning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is used to check if a string is permutation of the other.
 */
public class CheckPermutation {

    public static void main(String[] args) {
        String s1= "ajay";
        String s2 ="yaja";
        boolean result = checkIfPermutationUsingMap(s1, s2);
        printResult(result);
        result = checkIfPermutationUsingArray(s1, s2);
        printResult(result);
    }

    private static void printResult(boolean result) {
        if(result){
            System.out.println("Yes its is a permutation!");
        }else{
            System.out.println("No its not a permutation!");
        }
    }

    private static boolean checkIfPermutationUsingMap(String s1,String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        Map<Character, Integer> countMap = new HashMap<>(s1.length());
        for(char character : s1.toCharArray()){
            if(countMap.containsKey(character)) {
                Integer integer = countMap.get(character);
                countMap.put(character, integer+1);
            }else{
                countMap.put(character,1);
            }
        }

        for (char character: s2.toCharArray()){
            if(countMap.containsKey(character)) {
                Integer integer = countMap.get(character);
                integer--;
                if(integer<0){
                    return false;
                }
                countMap.put(character, integer);
            }else{
                return false;
            }
        }

        Iterator<Integer> iterator = countMap.values().iterator();
        while(iterator.hasNext()){
            if(iterator.next()!=0){
                return false;
            }
        }
        return true;
    }

    private static boolean checkIfPermutationUsingArray(String s1,String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        int[] array = new int[128];
        for(char character : s1.toCharArray()){
            array[character] ++;
        }
        for(char character : s2.toCharArray()){
            array[character] --;
            if(array[character]<0){
                return false;
            }
        }
        for(int integer : array){
            if(integer!=0){
                return false;
            }
        }
        return true;
    }
}
