package com.ajbose.learning.ffx;

import java.util.HashMap;
import java.util.Map;

public class FFXRepository {

    Map<String,Float> data = new HashMap<String,Float>(10);

    /**
     * Adds the rate to respository
     * @param souceCurrency
     * @param targetCurrency
     * @param conversionRateFloat
     */
    public void add(String souceCurrency, String targetCurrency, float conversionRateFloat) {
        data.put(souceCurrency+"_"+targetCurrency,conversionRateFloat);
    }

    /**
     * Returns the rate
     * @param souceCurrency
     * @param targetCurrency
     * @return
     */
    public Float get(String souceCurrency, String targetCurrency){
        return data.get(souceCurrency+"_"+targetCurrency);
    }
}
