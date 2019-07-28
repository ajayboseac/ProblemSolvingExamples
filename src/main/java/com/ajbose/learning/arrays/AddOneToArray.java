package com.ajbose.learning.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/add-one-to-number/
 * Given a non-negative number represented as an array of digits,
 *
 * add 1 to the number ( increment the number represented by the digits ).
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 *
 * If the vector has [1, 2, 3]
 *
 * the returned vector should be [1, 2, 4]
 *
 * as 123 + 1 = 124.
 *
 *  NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer.
 * For example, for this problem, following are some good questions to ask :
 * Q : Can the input have 0’s before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
 * A : For the purpose of this question, YES
 * Q : Can the output have 0’s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
 * A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.
 *  NOTE: You only need to implement the given function. Do not read input, instead use the arguments to the function. Do not print the output, instead return values as specified. Still have a doubt? Checkout Sample Codes for more details.
 */
public class AddOneToArray {
    public int[] plusOne(int[] A) {
        int toBeAdded =1 ;
        List<Integer> resultList = new ArrayList<Integer>(10);
        for(int i = A.length-1;i>=0;i--){
            int temp = A[i] +toBeAdded ;
            if(temp < 10){
               toBeAdded = 0; 
            }
            resultList.add(temp%10); 
        }
        if(toBeAdded > 0)
        {
            resultList.add(1);
        }
        int[] resultArray = null;
        boolean onlyZeroes = true;
        int j =0;
        for(int i=resultList.size()-1;i>=0;i--){
            if(resultList.get(i)!=0 && onlyZeroes){
                onlyZeroes = false;
                resultArray = new int[i+1];
            }
            if(!onlyZeroes){
                resultArray[j++] = resultList.get(i);
            }
        }
        return resultArray;
    }
}