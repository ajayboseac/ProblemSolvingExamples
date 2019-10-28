package com.ajbose.learning.dynamicprogramming;
/**
 *Given a string S, find the longest palindromic substring in S.
 *
 * Substring of string S:
 *
 * S[i...j] where 0 <= i <= j < len(S)
 *
 * Palindrome string:
 *
 * A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S.
 *
 * Incase of conflict, return the substring which occurs first ( with the least starting index ).
 *
 * https://www.interviewbit.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abb"));
    }
    public static String longestPalindrome(String str) {
        int n = str.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for(int i=0;i<n;i++){
            isPalindrome[i][i]=true;
        }

        int longestPalindromeStart =0;
        int longestPalindromeEnd =0;

        for(int i=0;i<n-1;i++){
            if(str.charAt(i)==str.charAt(i+1)){
                longestPalindromeStart=i;
                longestPalindromeEnd=i+1;
                isPalindrome[i][i+1]=true;
            }else {
                isPalindrome[i][i+1]=false;
            }
        }



        for(int gap=2;gap<n;gap++){
            for(int i=0;i<n-gap;i++){
                int  j= i+gap;
                if(isPalindrome[i+1][j-1]&&str.charAt(i)==str.charAt(j)){
                    isPalindrome[i][j]=true;
                    if(longestPalindromeEnd-longestPalindromeStart<j-i) {
                        longestPalindromeStart = i;
                        longestPalindromeEnd = j;
                    }
                }else{
                    isPalindrome[i][j] = false;
                }
            }
            
        }
        return str.substring(longestPalindromeStart,longestPalindromeEnd+1);
    }
}
