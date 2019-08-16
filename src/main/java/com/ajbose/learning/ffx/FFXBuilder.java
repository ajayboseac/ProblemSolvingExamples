package com.ajbose.learning.ffx;

import java.util.*;

public class FFXBuilder {

    List<String> currencyList = new ArrayList<>(10);
    FFXRepository ffxRepository;
    Map<String,List<String>> dependencyToDependentMap = new HashMap<String,List<String>>(10);
    Map<String,List<String>> dependentToDependenciesMap = new HashMap<String,List<String>>(10);

    FFXBuilder(FFXRepository ffxRepository) {
        this.ffxRepository = ffxRepository;
    }


    public void addCurrency(int index, String currency) {
        currencyList.add(index, currency);
    }

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
        //Check recursively for resolving all dependencies
        //If not resolved , Then add to dependency list

        String intermediateCurrency = conversionRate;
        conversionRateFloat = getConversionRateRecursively(sourceCurrency, targetCurrency,intermediateCurrency);
        if(conversionRateFloat !=-1){
            ffxRepository.add(sourceCurrency,targetCurrency,conversionRateFloat);
            resolveDependencies(sourceCurrency,targetCurrency);
            return;
        }
    }

    private void resolveDependencies(String sourceCurrency, String targetCurrency) {
        String key = sourceCurrency + "_" + targetCurrency;
        List<String> dependents = dependentToDependenciesMap.get(key);
        if(null == dependents || dependents.isEmpty()){
            return;
        }
        for(String dependent: dependents){
            String dependentSourceCurrency = dependent.split("_")[0];
            String dependentTargetCurrency = dependent.split("_")[1];
            String intermediateCurrency = dependent.split("_")[2];
            List<String> dependencies = dependentToDependenciesMap.get(dependentSourceCurrency + "_" + dependentTargetCurrency);
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
                float conversionRate = getConversionRateRecursively(dependentSourceCurrency, dependentTargetCurrency, intermediateCurrency);
                ffxRepository.add(dependentSourceCurrency, dependentTargetCurrency, conversionRate);
                resolveDependencies(dependentSourceCurrency, dependentTargetCurrency);
            }
        }
        dependentToDependenciesMap.remove(key);
    }

    private float getConversionRateRecursively(String sourceCurrency,String targetCurrency, String intermediateCurrency) {
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
