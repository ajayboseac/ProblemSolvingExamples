package com.ajbose.learning.ffx;

import java.io.IOException;

public class FFXtester {

    public static void main(String[] args) throws IOException {
        FFXRepository ffxRepository = new FFXRepository();
        FFXRepositoryBuilder ffxRepositoryBuilder = new FFXRepositoryBuilder(ffxRepository);
        FFXFileReader ffxFileReader = new FFXFileReader("src/main/resources/ref.csv", ffxRepositoryBuilder);
        ffxFileReader.readReferance();
        ffxFileReader.readData("src/main/resources/data.csv","src/main/resources/output.csv",ffxRepository);
    }
}
