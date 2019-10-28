package com.ajbose.learning.ffx;

import sun.misc.Cache;

import java.util.*;

public class FFXRepositoryBuilder {

    List<String> currencyList = new ArrayList<>(10);
    FFXRepository ffxRepository;
    Map<String,List<String>> dependencyToDependentMap = new HashMap<String,List<String>>(10);
    Map<String,List<String>> dependentToDependenciesMap = new HashMap<String,List<String>>(10);

    FFXRepositoryBuilder(FFXRepository ffxRepository) {
        this.ffxRepository = ffxRepository;
    }


    /**
     * Adds the currencies and their indexes.
     * @param index - Index of the currency
     * @param currency - The Currency code.
     */
    public void addCurrency(int index, String currency) {
        currencyList.add(index, currency);
    }

    /**
     * Adds the FFX rates.
     * @param sourceCurrencyIndex
     * @param targetCurrencyIndex
     * @param conversionRate
     */
    public void addFFX(int sourceCurrencyIndex, int targetCurrencyIndex, String conversionRate) {
        String sourceCurrency = currencyList.get(sourceCurrencyIndex);
        String targetCurrency = currencyList.get(targetCurrencyIndex);
        float conversionRateFloat;
        try {
            conversionRateFloat = Float.parseFloat(conversionRate);
            ffxRepository.add(sourceCurrency,
                    targetCurrency, conversionRateFloat);
            return;
        } catch (Throwable e) {
            //This is expected.
        }

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
//        strings.c
//        strings.stream().forEach(it ->{this.ffxRepository});

        //Check recursively for resolving all dependencies
        //If not resolved , Then add to dependency list

        String intermediateCurrency = conversionRate;
        conversionRateFloat = getConversionRateFromIntermediateCurrency(sourceCurrency, targetCurrency,intermediateCurrency);
        if(conversionRateFloat !=-1){
            ffxRepository.add(sourceCurrency,targetCurrency,conversionRateFloat);
            resolveDependencies(sourceCurrency,targetCurrency);
            return;
        }
    }

    private void resolveDependencies(String sourceCurrency, String targetCurrency) {
        String key = sourceCurrency + "_" + targetCurrency;
        List<String> dependents = dependentToDependenciesMap.get(key);
        Optional<String> first = new ArrayList<String>(10).stream().findFirst();
        if(null == dependents || dependents.isEmpty()){
            return;
        }
        for(String dependent: dependents){
            String dependentSourceCurrency = null;
            String dependentTargetCurrency = null;
            String intermediateCurrency = null;
            List<String> dependencies = null;
            try {
                dependentSourceCurrency = dependent.split("_")[0];
                dependentTargetCurrency = dependent.split("_")[1];
                intermediateCurrency = dependent.split("_")[2];
                dependencies = dependentToDependenciesMap.get(dependentSourceCurrency + "_" + dependentTargetCurrency);
            } catch (ClassCastException|NullPointerException e) {
                e.getClass();
            }
            if(dependencies !=null ){
                Iterator<String> iterator = dependencies.iterator();
                while(iterator.hasNext()){
                    if("key".equals(iterator.next() ) ){
                        iterator.remove();
                    }
                }
            }
            if(dependencies==null || dependencies.isEmpty()) {
                dependentToDependenciesMap.remove(dependentSourceCurrency + "_" + dependentTargetCurrency);
                float conversionRate = getConversionRateFromIntermediateCurrency(dependentSourceCurrency, dependentTargetCurrency, intermediateCurrency);
                ffxRepository.add(dependentSourceCurrency, dependentTargetCurrency, conversionRate);
                resolveDependencies(dependentSourceCurrency, dependentTargetCurrency);
            }
        }
        dependentToDependenciesMap.remove(key);
    }

    private float getConversionRateFromIntermediateCurrency(String sourceCurrency, String targetCurrency, String intermediateCurrency) {
        if(targetCurrency.equals(intermediateCurrency )|| sourceCurrency.equals(intermediateCurrency)){
            throw new RuntimeException("Cyclic Dependency in tha mapping: "+ sourceCurrency +"-->"+targetCurrency);
        }
        Float rate1 = ffxRepository.get(sourceCurrency, intermediateCurrency);
        Float rate2 = ffxRepository.get(intermediateCurrency,targetCurrency);
        if(null==rate1){
            addDependency(sourceCurrency+"_"+intermediateCurrency,sourceCurrency+"_"+targetCurrency,intermediateCurrency);
        }
        if(null==rate2){
            addDependency(intermediateCurrency+"_"+targetCurrency,sourceCurrency+"_"+targetCurrency,intermediateCurrency);
        }
        if(null==rate1 || null==rate2){
            return  -1;
        }
        return  rate1*rate2;
    }

    private void addDependency(String dependency, String dependent, String intermediateCurrency) {

        List<String> dependents = dependencyToDependentMap.get(dependency);
        List<String> dependencies = dependentToDependenciesMap.get(dependent);
        if(dependents!=null){
            if(dependencies!=null){
                if(dependencies.contains(dependency)){
                    throw new RuntimeException("Cyclic Dependency: "+ dependent +" <--> " + dependency);
                }
                if(!dependencies.contains(dependency)) {
                    dependencies.add(dependency);
                }
            }else{
                dependencies = new ArrayList<String>();
                dependencies.add(dependency);
                dependentToDependenciesMap.put(dependent,dependencies);
            }
            if(!dependents.contains(dependent + "_" + intermediateCurrency)) {
                dependents.add(dependent + "_" + intermediateCurrency);
            }
            return;
        }
        dependents = new ArrayList<String>();
        dependents.add(dependent+"_"+"intermediateCurrency");
        dependencyToDependentMap.put(dependency,dependents);
    }
}
