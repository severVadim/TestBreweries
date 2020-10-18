package com.breweries.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);


    public static String getJsonStringFromFile(String filePath) {
        try {
            return new String(Utils.class.getResourceAsStream(filePath).readAllBytes());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public static <T> List<T> parseJsonIntoObjectsList(String jsonBody, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(jsonBody, OBJECT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, tClass));
        } catch (JsonProcessingException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }


    public static <T> T parseJsonIntoObject(String jsonBody, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(jsonBody, tClass);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }
}
