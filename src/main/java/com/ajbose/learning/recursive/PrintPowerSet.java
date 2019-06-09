package com.ajbose.learning.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintPowerSet {
    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>();
        set.add(99);
        set.add(98);
        set.add(97);
        set.add(96);
        set.add(95);
        HashSet<List<Integer>> powerSet = new HashSet<>();
        calculatePowerSet(set, powerSet);
        System.out.println(powerSet.toString());
        System.out.println("***************************************");
        Set<List<Integer>> lists = calculatePowerSetNoDuplicateGeneration(set, 0);
        System.out.println(lists.toString());
    }

    /**
     * Here duplicate sets get generated but the set takes care of duplicate sets.
     * @param set
     * @param powerSet
     */
    private static void calculatePowerSet(List<Integer> set, Set<List<Integer>> powerSet) {
        if(set.size()==1){
            return;
        }
        for(int i=0;i<set.size();i++){
            ArrayList<Integer> localSet = new ArrayList<>(set);
            localSet.remove(i);
            if(!powerSet.contains(localSet)) {
                powerSet.add(localSet);
                calculatePowerSet(localSet,powerSet);
            }
        }
    }

    /**
     * Powerset of the current set is
     * @param set
     * @param index
     */
    private static Set<List<Integer>> calculatePowerSetNoDuplicateGeneration(List<Integer> set, int index) {
        Set<List<Integer>> powerSet = new HashSet<List<Integer>> ();
        if(index==set.size()){
            powerSet.add(new ArrayList<Integer>());
            return powerSet;
        }
        int item = set.get(index);
        Set<List<Integer>> subSetPowerSet = calculatePowerSetNoDuplicateGeneration(set,index+1);
        Set<List<Integer>> newSubSet = new HashSet< List<Integer>>(10);
        for(List<Integer> curentSet:subSetPowerSet){
            List<Integer> newSet = new ArrayList<Integer>(curentSet);
            newSet.add(item);
            newSubSet.add(newSet);
        }

        powerSet.addAll(newSubSet);
        powerSet.addAll(subSetPowerSet);
        return  powerSet;
    }

}
