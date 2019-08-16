package com.ajbose.learning.arena;

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
//import for Scanner and other utility classes
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {
       
        //Scanner
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Item> items = new ArrayList<Item>(10);
        for(int i=0;i<n;i++){
            int numberOfValues = s.nextInt();
            int numberOfWeights = s.nextInt();
            String[] values = s.nextLine().split(" ");
            String[] weights = s.nextLine().split(" ");
            
            for(int j=0;j<numberOfValues;j++){
                items.add(new TestClass().new Item(Integer.valueOf(weights[j]),Integer.valueOf(values[j])));
            }
        }
        
        Collections.sort(items);
        int totalValue = 0;
        int totalWeight =0;
        for(Item item:items){
            if(totalWeight+ item.weight > 1000){
                break;
            }
            totalWeight += item.weight;
            totalValue += item.value;
        }
        System.out.println(totalValue);
        
    }
    
    class Item implements Comparable{
        int weight;
        int value;
        public Item(int weight,int value){
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Object item1) {
            return ((Integer)((Item)item1).value).compareTo(this.value);
        }
    }
}
