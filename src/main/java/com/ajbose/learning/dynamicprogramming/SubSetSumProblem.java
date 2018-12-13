package com.ajbose.learning.dynamicprogramming;

import java.util.Arrays;

/**
 *
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal
 * to given sum.
 *
 * Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 */
public class SubSetSumProblem {

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 7, 1};
        int sum = 9;
        boolean b = ifThereisASubSet(set, sum,6);
        System.out.println(b);
    }

    private static boolean ifThereisASubSet(int[] set, int sum,int arraySize) {
        if(sum==0){
           return true;
        }if(sum<=0){
            return false;
        }
        if(arraySize==0){
            return false;
        }
        return ifThereisASubSet(set,sum-set[arraySize-1],arraySize-1)|| ifThereisASubSet(set,sum,arraySize-1);
    }
}
