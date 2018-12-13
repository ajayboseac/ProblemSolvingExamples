package com.ajbose.learning.dynamicprogramming;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are
 * subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.
 */
public class LongestCommonSubSequecne {

    public static void main(String[] args) {
//        String s1= "ABCDGH";
//        String s2= "AEDFHR";

        String s1= "AGGTAB";
        String s2= "GXTXAYB";

        System.out.println("Length of longest common subsequcne is "+lcs(s1,s2,0,0));
    }

    private static int lcs(String s1, String s2, int m, int n) {
        if(s1.length()==m||s2.length()==n){
            return 0;
        }
        if(s1.charAt(m)==s2.charAt(n)){
            return 1+lcs(s1,s2,m+1,n+1);
        }else{
            return Math.max(lcs(s1,s2,m+1,n),lcs(s1,s2,m,n+1));
        }
    }
}
