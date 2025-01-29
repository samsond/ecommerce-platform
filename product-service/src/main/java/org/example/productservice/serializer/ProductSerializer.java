package org.example.productservice.serializer;

import org.example.productservice.dto.ProductDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object value) {
        try {
            if (value instanceof List) {
                return serializeList((List<ProductDTO>) value);
            } else {
                return serializeScalar((ProductDTO) value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) {
        try {
            return deserializeScalar(bytes);
        } catch (Exception e) {
            try {
                return deserializeList(bytes);
            } catch (Exception ex) {
                throw new SerializationException("Error deserializing object", ex);
            }
        }
    }

    private byte[] serializeScalar(ProductDTO value) throws Exception {
        return JsonSerializer.serialize(value);
    }

    private byte[] serializeList(List<ProductDTO> value) throws Exception {
        return JsonSerializer.serialize(value);
    }

    private ProductDTO deserializeScalar(byte[] bytes) throws Exception {
        return JsonSerializer.deserialize(bytes, ProductDTO.class);
    }

    private List<ProductDTO> deserializeList(byte[] bytes) throws Exception {
        return JsonSerializer.deserializeList(bytes, ProductDTO.class);
    }
}
