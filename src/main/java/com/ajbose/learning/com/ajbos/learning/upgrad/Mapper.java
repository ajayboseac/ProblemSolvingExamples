package com.ajbose.learning.com.ajbos.learning.upgrad;

import java.io.*;
import java.util.List;

public class Mapper implements Runnable {
    File file;
    List<Reducer> reducers;

    Mapper(File file, List<Reducer> reducers) {
        this.file=file;
        this.reducers=reducers;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line!=null){
                String[] split = line.split("\\s+");
                for(int i=0;i<split.length;i++){
                    addToReducer(split[i]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Mapper done!!");
    }

    private void addToReducer(String s) {
        Reducer reducer = reducers.get(Math.abs(s.hashCode() % 3));
        reducer.add(s);
    }
}
