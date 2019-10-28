package com.ajbose.learning.recursive;

public class CountNumberOfPalindromeSibStrings {

    public static void main(String[] args) {
        String string = "aaskjdkkdj";
        int count = countPalindromeSubStrings(string);

        System.out.println("Number of Palindrome Strings: "+ count);
        count = countPalindromeSubStringsDP(string);

        System.out.println("Number of Palindrome Strings: "+ count);
    }

    private static int countPalindromeSubStringsDP(String string) {
        int n = string.length();
        int[][] palindromeCount = new int[n][n];
        boolean[][] isPalindrome = new boolean[n][n];
        for(int i=0;i<n;i++){
            palindromeCount[i][i] =1;
            isPalindrome[i][i]=true;
        }

        //For strings of length 2
        for(int i=0;i<n-1;i++){
            if(string.charAt(i)==string.charAt(i+1)){
                palindromeCount[i][i+1] = 2;
                isPalindrome[i][i+1] = true;
            }else{
                palindromeCount[i][i+1] =1;
                isPalindrome[i][i+1] = false;
            }
        }

        for(int gap=2;gap<n-1;gap++){
            for(int i=0;i<n-gap;i++){
                int j= i+gap;
                if(isPalindrome[i+1][j]&&string.charAt(i)==string.charAt(j)){
                    isPalindrome[i][j] = true;
                    palindromeCount[i][j] = 1+palindromeCount[i+1][j]+palindromeCount[i][j-1]-palindromeCount[i+1][j-1];
                }else{
                    isPalindrome[i][j] = false;
                    palindromeCount[i][j] = 1+palindromeCount[i+1][j]+palindromeCount[i][j-1]-palindromeCount[i+1][j-1];
                }
            }
        }

        return palindromeCount[0][n-1];
    }

    /**
     * The obvious o(n^3 ) way
     * @param string
     * @return
     */
    private static int countPalindromeSubStrings(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j < string.length(); j++) {
                String subString = string.substring(i, j);
                if (subString.equals(new StringBuffer(subString).reverse().toString())) {
                    count++;
                }
            }
        }
        return count;
    }
}
