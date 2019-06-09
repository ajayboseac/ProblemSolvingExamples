package com.ajbose.learning.dynamicprogramming;

/**
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm}
 * valued coins, how many ways can we make the change? The order of coins doesn’t matter.
 * <p>
 * For example, for N = 4 and S = {1,2,3}, there are four solutions:
 * {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For N = 10 and S = {2, 5, 3, 6},
 * there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
 * So the output should be 5.
 */
public class CoinChangeProblem {

    public static void main(String[] args) {
        int[] availableDenominations = new int[]{1, 2, 3};
        int amount = 10;

        //The wrong way.
        int combinations = calculateNumberOfCombinations(amount, availableDenominations);
        System.out.println(combinations);

        //The correct way.

        combinations = calculateNumberOfCombinationsCorrectWay(amount, availableDenominations,3);
        System.out.println(combinations);

        combinations = calculateNumberOfCombinationsDP(amount, availableDenominations);
        System.out.println(combinations);

        //TODO : Need to still add the dynamic programming way of solving the problem!
    }

    /**
     * Recursive function - This was my initial approach and it doesnt work. As it considers the order.
     * I need to  find a way which is agnostic of the order.
     *
     * @param amount
     * @param availableDenominations
     */
    private static int calculateNumberOfCombinations(int amount, int[] availableDenominations) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < availableDenominations.length; i++) {
            count += calculateNumberOfCombinations(amount - availableDenominations[i], availableDenominations);
        }
        return count;
    }


    /**
     * Recursive function - This was my initial approach and it doesnt work. As it considers the order.
     * I need to  find a way which is agnostic of the order.
     *
     * @param amount
     * @param availableDenominations
     */
    private static int calculateNumberOfCombinationsCorrectWay(int amount, int[] availableDenominations, int sizeOfAvailableDenominations) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        int count = 0;

        if(sizeOfAvailableDenominations<=0){
            return 0;
        }


        return calculateNumberOfCombinationsCorrectWay(amount - availableDenominations[sizeOfAvailableDenominations - 1],
                availableDenominations, sizeOfAvailableDenominations) + calculateNumberOfCombinationsCorrectWay(amount , availableDenominations, sizeOfAvailableDenominations-1);

    }

    private static int calculateNumberOfCombinationsDP(int amount, int[] availableDenominations){
        int[] combinations = new int[amount+1];
        combinations[0]= 1;
        for(int i=0;i<availableDenominations.length;i++){
            for(int j=availableDenominations[i];j<amount+1;j++){
                combinations[j] += combinations[j-availableDenominations[i]];
            }
        }
        return combinations[amount];

    }


}
