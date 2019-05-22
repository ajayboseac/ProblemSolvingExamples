package com.ajbose.learning.dynamicprogramming;

import java.util.*;

public class PrintAllSubsetsOfASet {
    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>(10);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        List<List<Integer>> allSubSets = getAllSubSets(set, 0);
        System.out.println(allSubSets);
    }

    private static List<List<Integer>> getAllSubSets(List<Integer> set, int index) {
        List<List<Integer>> allSubSets;
        if(set.size()==index){
            allSubSets = new ArrayList<>();
            //Add the null set
            allSubSets.add(new ArrayList<>());
        }else{
            allSubSets = getAllSubSets(set,index+1);
            Integer item = set.get(index);
            List<List<Integer>> moreSubSets = new ArrayList<List<Integer>>();
            for(List<Integer> subset : allSubSets ) {
                ArrayList<Integer> newSet = new ArrayList<>(subset);
                newSet.add(item);
                moreSubSets.add(newSet);
            }
            allSubSets.addAll(moreSubSets);
        }
        return allSubSets;
    }
}
