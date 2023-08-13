package org.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProvider {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper;
        defaultObjectMapper = new ObjectMapper();
        //can be configured here
        return  defaultObjectMapper;
    }

    public static String objectToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static JsonNode readTree(String json) throws JsonProcessingException{
        return objectMapper.readTree(json);
    }
}
