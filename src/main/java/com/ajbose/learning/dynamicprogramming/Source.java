package com.ajbose.learning.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Source {
    static Map<Credentials, AccountInfo> database = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int input = Integer.parseInt(reader.readLine());
            switch (input) {
                case 2:
                    signup(reader);
                    break;
                case 1:
                    AccountInfo signin = signin(reader);
                    if(null==signin){
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(signin.accountNumber);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static AccountInfo signin(BufferedReader reader) throws IOException {
        String userName = reader.readLine();
        String password = reader.readLine();
        long phoneNumber = Long.parseLong(reader.readLine());
        Credentials credentials = new Credentials(userName, password, phoneNumber);
        return database.get(credentials);
    }

    private static void signup(BufferedReader reader) throws IOException {
        String userName = reader.readLine();
        String password = reader.readLine();
        long phoneNumber = Long.parseLong(reader.readLine());
        String accountName = reader.readLine();
        long initialBalance= Long.parseLong(reader.readLine());
        int accountNumber = Integer.parseInt(reader.readLine());
        Credentials credentials = new Credentials(userName, password, phoneNumber);
        AccountInfo accountInfo = new AccountInfo(accountName, accountNumber, initialBalance);
        if(!database.containsKey(credentials)) {
            database.put(credentials, accountInfo);
        }
    }
}

class Credentials {
    String userName;
    String password;
    long phoneNumber;

    Credentials(String userName, String password, long phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hashCode = 1;
        hashCode = hashCode + userName.hashCode()*prime;
        hashCode = hashCode + password.hashCode()*prime;
        hashCode = hashCode + ((Long)phoneNumber).hashCode()*prime;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return  true;
        }
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Credentials)){
            return  false;
        }

        Credentials credentials = (Credentials) obj;
        if(credentials.phoneNumber!=this.phoneNumber){
            return  false;
        }
        if(!credentials.password.equals(this.password)){
            return false;
        }
        if(!credentials.userName.equals(this.userName)){
            return false;
        }
        return true;
    }
}

class AccountInfo {
    String accountName;
    int accountNumber;
    long balannce;

    AccountInfo(String accountName,int accountNumber,long balannce){
        this.accountName=accountName;
        this.accountNumber=accountNumber;
        this.balannce=balannce;
    }

    //this class contains account name, balance and account Number
    //this class object is used as value to store in hashmap against the user credentials key.
}