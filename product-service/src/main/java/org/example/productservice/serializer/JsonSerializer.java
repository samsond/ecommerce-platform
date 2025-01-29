package org.example.productservice.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class JsonSerializer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static byte[] serialize(Object object) throws Exception {
        String jsonString = objectMapper.writeValueAsString(object);
        return jsonString.getBytes();
    }

    public static <T> T deserialize(byte[] raw, Class<T> reference) throws Exception {
        return objectMapper.readValue(raw, reference);
    }

    public static <T> List<T> deserializeList(byte[] raw, Class<T> reference) throws Exception {
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, reference);
        return objectMapper.readValue(raw, listType);
    }
}
