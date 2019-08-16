package com.ajbose.learning.ffx;

import java.io.IOException;

public class FFXtester {

    public static void main(String[] args) throws IOException {
        FFXRepository ffxRepository = new FFXRepository();
        FFXBuilder ffxBuilder = new FFXBuilder(ffxRepository);
        FFXFileReader ffxFileReader = new FFXFileReader("src/main/resources/ref.csv", ffxBuilder);
        ffxFileReader.readReferance();
        ffxFileReader.readData("src/main/resources/data.csv","src/main/resources/output.csv",ffxRepository);
    }
}
