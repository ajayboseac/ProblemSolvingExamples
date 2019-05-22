package com.ajbose.learning.greedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given weights and values of n items, we need to put these items in
 * a knapsack of capacity W to get the maximum total value in the knapsack.
 *
 * Input:
 *   Items as (value, weight) pairs
 *   arr[] = {{60, 10}, {100, 20}, {120, 30}}
 *   Knapsack Capacity, W = 50;
 * Output:
 *   Maximum possible value = 220
 *   by taking items of weight 20 and 30 kg
 */
public class KnapStackProblem {

    public static void main(String[] args){
        int[] values = {60,100,120};
        int[] weights = {10,20,30};
        int capacity = 50;
        int maxValue = findMaxValue(weights, values, capacity);
        System.out.println(maxValue);
    }

    private static int findMaxValue(int[] weights,int[] values, int capacity){
        ValueItem[] valueItems = new ValueItem[weights.length];
        for (int i=0;i<weights.length;i++){
            valueItems[i] = new ValueItem(weights[i],values[i]);
        }
        Arrays.sort(valueItems, new Comparator<ValueItem>() {
            @Override
            public int compare(ValueItem o1, ValueItem o2) {
                //Sort in descending order of cost.
                return Integer.valueOf(o2.cost).compareTo(o1.cost);
            }
        });
        int remainingCapacity = capacity;
        int i=0;
        int value =0;
        while(remainingCapacity>0&& i<valueItems.length){
            if(remainingCapacity>valueItems[i].weight) {
                remainingCapacity = remainingCapacity - valueItems[i].weight;
                value += valueItems[i].value;
            }else{
                double fraction = ((double) remainingCapacity)/(double)( valueItems[i].weight);
                value += fraction*valueItems[i].value;
                break;
            }
            i++;
        }
        return value;
    }

    private static class ValueItem{
        int weight;
        int value;
        int cost;

        ValueItem(int weight,int value){
            this.value =value;
            this.weight =weight;
            this.cost = value/weight;
        }
    }

}
