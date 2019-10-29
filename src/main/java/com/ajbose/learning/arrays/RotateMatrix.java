package com.ajbose.learning.arrays;

import java.util.Arrays;

public class RotateMatrix {

    public static void main(String[] args) {

        int [][]matrix= new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        printMatrix(matrix);
        rotate(matrix);
        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix){
        for (int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]);
                if(j+1!=matrix[i].length){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public static boolean rotate(int[][] matrix) {
        int n = matrix.length;
        if (matrix[0].length != n) {
            return false;
        }

        for(int layer=0;layer<n/2;layer++){
            int first = layer ;
            int last = n-1-layer;
            for(int i=first;i<last;i++){
                int offset = i-first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last-offset][first];
                matrix[last-offset][first] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = top;
            }

        }

        return true;
    }
}
