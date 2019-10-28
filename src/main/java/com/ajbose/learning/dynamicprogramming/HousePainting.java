package com.ajbose.learning.dynamicprogramming;

public class HousePainting {

    public static void main(String[] args) {
        int[][] costOfHouses = new int[][]{{10, 20, 15}, {20, 30, 5}, {40, 60, 7}};
        System.out.println(findMinCost(costOfHouses, 0, -1));
        System.out.println(findMinCostDP(costOfHouses));
    }

    private static int findMinCost(int[][] costOfHouses, int houseNumber, int previousColor) {
        if (houseNumber >= costOfHouses.length) {
            return 0;
        }
        int[] costOfHouse = costOfHouses[houseNumber];
        int totalCostOfHouse = Integer.MAX_VALUE;
        for (int color = 0; color < costOfHouse.length; color++) {
            if (color != previousColor) {
                totalCostOfHouse = Math.min(totalCostOfHouse,
                        costOfHouse[color] + findMinCost(costOfHouses, houseNumber + 1, color));
            }
        }
        return totalCostOfHouse;
    }

    private static int findMinCostDP(int[][] costOfHouses) {

        for (int houseNumber = 1; houseNumber < costOfHouses.length; houseNumber++) {
            int[] costforColors = costOfHouses[houseNumber];
            for (int color = 0; color < costforColors.length; color++) {
                costforColors[color] += findMinimumCostOfHouse(costOfHouses[houseNumber - 1], color);
            }
        }
        return findMinimumCostOfHouse(costOfHouses[costOfHouses.length-1],-1);
    }

    private static int findMinimumCostOfHouse(int[] costOfHouse, int color) {
        Integer minimum = Integer.MAX_VALUE;
        for (int i = 0; i < costOfHouse.length; i++) {
            if (color != i) {
                minimum = Math.min(minimum, costOfHouse[i]);
            }
        }
        return minimum;
    }
}
