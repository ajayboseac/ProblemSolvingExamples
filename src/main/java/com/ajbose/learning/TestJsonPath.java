package com.ajbose.learning;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestJsonPath {
    public static void main(String[] args) {
        String jsonInput;
        try {
            jsonInput = FileUtils.readFileToString(new File("src/main/resources/test.json"), "UTF-8");
            DocumentContext parse = JsonPath.parse(jsonInput);
            System.out.println(parse.read("BNAList[?(@.BNAId=BNA-1011)].BNAId").toString());
//            System.out.println(parse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
