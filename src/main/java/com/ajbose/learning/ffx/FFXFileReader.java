package com.ajbose.learning.ffx;

import java.io.*;
import java.util.Scanner;

public class FFXFileReader {
    String fileName;
    FFXBuilder ffxBuilder;

    public FFXFileReader(String fileName, FFXBuilder ffxBuilder) {
        this.fileName = fileName;
        this.ffxBuilder = ffxBuilder;
    }


    public void readReferance() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        int i =0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] split = line.split(",");
            //This is the list of currencies
            if(i==0){
                for(int j=1;j<split.length;j++){
                    ffxBuilder.addCurrency(j-1,split[j]);
                }
                i++;
                continue;
            }

            for(int j=1;j<split.length;j++){
                ffxBuilder.addFFX(i-1,j-1,split[j]);
            }
            i++;
        }
        scanner.close();
    }

    public void readData(String datafile,String targetFile,FFXRepository repository) throws IOException {
        Scanner scanner = new Scanner(new FileReader(datafile));
        File file = new File(targetFile);
        if(file.exists())
        {
            file.delete();
        }
        boolean fileCreated = file.createNewFile();
        if(false==fileCreated){
            throw new RuntimeException("Exception while creating file.");
        }
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(targetFile));
        int i =0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] split = line.split(",");
            //This is the list of currencies
            if(i==0){
                fileWriter.write("FromCurrency,ToCurrency,FromAmount,ToAmount\n");
                i++;
                continue;
            }
            String sourceCurrency = split[0];
            String targetCurrency = split[1];
            float amount = Float.parseFloat(split[2]);
            float targetAmount = convertAmount(sourceCurrency,targetCurrency,repository,amount);
            fileWriter.write(sourceCurrency+","+targetCurrency+","+amount+","+targetAmount+"\n");
            fileWriter.flush();
            i++;
        }
        fileWriter.close();
    }

    private float convertAmount(String sourceCurrency, String targetCurrency, FFXRepository repository,float amount) {
        return repository.get(sourceCurrency,targetCurrency)*amount;
    }
}
