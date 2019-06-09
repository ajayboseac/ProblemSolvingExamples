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
    }

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

}
