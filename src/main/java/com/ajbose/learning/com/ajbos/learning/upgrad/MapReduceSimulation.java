package com.ajbose.learning.com.ajbos.learning.upgrad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapReduceSimulation {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Reducer reducer1 = new Reducer();
        Reducer reducer2 = new Reducer();
        Reducer reducer3 = new Reducer();
        List<Reducer> reducers = new ArrayList<>(10);
        reducers.add(reducer1);
        reducers.add(reducer2);
        reducers.add(reducer3);

        Mapper mapper1 = new Mapper(new File("src/main/resources/TextFile1.txt"), reducers);
        Mapper mapper2 = new Mapper(new File("src/main/resources/TextFile2.txt"),reducers);

        ExecutorService executorService = Executors.newFixedThreadPool(20, Executors.defaultThreadFactory());
        Future<?> reducer1Future = executorService.submit(reducer1);
        Future<?> reducer2Future = executorService.submit(reducer2);
        Future<?> reducer3Future = executorService.submit(reducer3);

        Future<?> mapperFuture1 = executorService.submit(mapper1);
        Future<?> mapperFuture2 = executorService.submit(mapper2);

        mapperFuture1.get();
        mapperFuture2.get();


        reducer1.finish();
        reducer2.finish();
        reducer3.finish();






        // Start two threads - > Which processes the files
        //Start Three threads which are aggregator(s).
        //Star Another Thread which is like a Master Aggregator
    }
}
