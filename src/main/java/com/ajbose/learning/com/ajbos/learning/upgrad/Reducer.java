package com.ajbose.learning.com.ajbos.learning.upgrad;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Reducer implements Runnable{

    ArrayBlockingQueue<String> queue;

    Map<String,Integer> map;

    boolean run;

    public Reducer() {
        queue = new ArrayBlockingQueue<String>(10000000, false);
        map = new ConcurrentHashMap<String,Integer>(1000000);
        run = true;
    }

    @Override
    public void run() {
        while(run || !queue.isEmpty()){
            try {
                String element = queue.poll(10,TimeUnit.MILLISECONDS);
                if(null!=element){
                    handleElement(element);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println("Reducer Done!!!");
    }

    private void handleElement(String element) {
        System.out.println("Added: "+element);
        if(!map.containsKey(element)) {
            map.put(element, 1);
            return;
        }
        Integer count = map.get(element);
        map.put(element,count+1);
        System.out.println("Processed "+element+" : "+(count+1));
    }

    public void add(String element){
        queue.offer(element);
    }

    public void finish(){
        System.out.println(map.toString());
        run=false;
//        Thread.currentThread().interrupt();
    }

}
