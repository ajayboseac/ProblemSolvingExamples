package com.ajbose.learning.recursive;

public class ValidCombinationOfPranthesis {

    public static void main(String[] args) {
        int n=5;
        printValidCombinationOfParanthesis(n,n,n,"");
    }

    private static void printValidCombinationOfParanthesis(int n , int openRemaining,int closeRemaining,String currentString){
        if(openRemaining == 0&&closeRemaining==0){
            System.out.println(currentString);
            return;
        }
        if(openRemaining<closeRemaining&&closeRemaining>0){
            printValidCombinationOfParanthesis(n,openRemaining,closeRemaining-1,currentString+")");
        }
        if(openRemaining>0) {
            printValidCombinationOfParanthesis(n, openRemaining - 1, closeRemaining, currentString + "(");
        }
    }
}
