package com.ajbose.learning.arena;

/**
 * Two letter strings are the strings consisting of only two letters "X" and "Y". A string is "super two letter string" if
 * <p>
 * a) It does not have leading "X" letters.
 * b) It does not contain P consecutive "X" letters.
 * Your task is to find total number of Super two letter strings of length N.
 * <p>
 * Input :
 * <p>
 * The first line contains the number of test cases T . Each test case consists of two space separated integers - N and P .
 * <p>
 * Output :
 * <p>
 * For each test case output total number of Super two letter strings of length N modulo 1000000007(10^9+7).
 * <p>
 * Constraints :
 * <p>
 * 1 <= T <= 100
 * <p>
 * 1 <= N <= 10^4
 * <p>
 * 1 <= P <= 10
 * <p>
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/super-two-letter-strings/
 */
public class SuperTwoLetterStrings {

    public static void main(String[] args) {
        int n = 4;
        int number = findNumberOfSuperTwoLetterStrings(n - 1, 2, 0);
        System.out.println("number of super two letter strings: " + number);
    }

    private static int findNumberOfSuperTwoLetterStrings(int length, int p, int numberOfXInPrefix) {

        if (length == 1) {

            if (numberOfXInPrefix == p-1) {
                return 1;
            } else {
                return 2;
            }
        }

        if (numberOfXInPrefix == p-1) {
            return findNumberOfSuperTwoLetterStrings(length - 1, p, 0);
        } else {
            return findNumberOfSuperTwoLetterStrings(length - 1, p, numberOfXInPrefix + 1) + findNumberOfSuperTwoLetterStrings(length - 1, p, numberOfXInPrefix);
        }
    }
}
