package com.ajbose.learning.recursive;

import java.util.Arrays;

public class EightQueensProblem {

    static final int GRID_SIZE=8;

    public static void main(String[] args) {
        printCombinations(new int[GRID_SIZE],0);
    }

    private static void printCombinations(int[] columns,int row){
        if(row==GRID_SIZE-1){
            System.out.println(Arrays.toString(columns));
        }
        for(int i=0;i<GRID_SIZE;i++){
            if(ifValid(i,row,columns)){
                int[] clone = columns.clone();
                columns[row] = i;
                printCombinations(columns,row+1);
            }
        }
    }

    private static boolean ifValid(int column, int currentRow, int[] columns) {

        for(int row=0;row<currentRow;row++){
            if(columns[row]==column){
                return false;
            }
            if(row-columns[row]==column-currentRow){
                return false;
            }
        }
        return true;
    }
}
