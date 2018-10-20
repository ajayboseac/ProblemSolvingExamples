package com.ajbose.learning;

/**
 * This program check if a string is just one edit away from the other string or not ?
 */
public class IsOneEditAway {

    public static void main(String[] args) {
        String s1 = "ajay";
        String s2 = "ajy";

        String s3 = "anju";
        String s4 = "aju";

        String s5 = "anju";
        String s6 = "ajnu";
        System.out.println(isOneEditAway(s1,s2));
        System.out.println(isOneEditAway(s3,s4));
        System.out.println(isOneEditAway(s5,s6));
    }

    private static boolean isOneEditAway(String s1,String s2){
        if(Math.abs(s1.length()-s2.length())>1){
            return false;
        }
        if(s1.equals(s2)){
            return true;
        }
        if(s1.length() ==s2.length()){
            if(s1.equals(replacechar(s1,s2))){
                return true;
            }
        }
        if(s1.length()+1 ==s2.length()){
            if(s1.equals(insertChar(s2,s1))){
                return true;
            }
        }
        if(s1.length()-1 ==s2.length()){
            if(s1.equals(insertChar(s1,s2))){
                return true;
            }
        }
        return false;
    }

    private static String insertChar(String s1, String s2) {
        StringBuilder stringBuilder = new StringBuilder(s2);
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                stringBuilder.insert(i,s1.charAt(i));
                break;
            }
        }
        return stringBuilder.toString();
    }

    private static String replacechar(String s1, String s2) {
        StringBuilder stringBuilder = new StringBuilder(s2);
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                stringBuilder.setCharAt(i,s1.charAt(i));
                break;
            }
        }
        return stringBuilder.toString();
    }
}
