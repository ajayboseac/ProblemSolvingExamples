package com.ajbose.learning.dynamicprogramming;

/**
 * Given a rod of length n inches and an array of prices that contains prices of
 * all pieces of size smaller than n. Determine the maximum value obtainable by
 * cutting up the rod and selling the pieces. For example, if length of the rod
 * is 8 and the values of different pieces are given as following, then the maximum
 * obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 * <p>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 */
public class CuttingARod {

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int[] lengths = {1, 2, 3, 4, 5, 6, 7, 8};
        int maxPrice = findMaxPrice(prices, lengths, 8, 8);
        System.out.println(maxPrice);
    }

    private static int findMaxPrice(int[] prices, int[] lengths, int lengthOfTheRod,int sizeOfArray) {
        if(sizeOfArray == 0){
            return 0;
        }
        if(lengthOfTheRod <=0){
            return 0;
        }

        return Math.max(findMaxPrice(prices, lengths, lengthOfTheRod, sizeOfArray-1),prices[sizeOfArray-1]
                +findMaxPrice(prices,lengths,lengthOfTheRod-lengths[sizeOfArray-1],sizeOfArray));
    }
}
