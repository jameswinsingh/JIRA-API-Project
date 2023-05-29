package com.jira.api.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJsonDataUtil {

    /**
     *
     * @param filePath contains file path of JSON file.
     * @param content to read form JSON file.
     * @return read data.
     */
    public static Object readJsonData(String filePath, String content) {
        JSONParser parser = new JSONParser();
        Object dataToRead = null;
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            dataToRead = jsonObject.get(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataToRead;
    }
}
