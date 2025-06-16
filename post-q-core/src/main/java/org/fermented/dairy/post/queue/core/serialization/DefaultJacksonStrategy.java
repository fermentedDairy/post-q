package org.fermented.dairy.post.queue.core.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DefaultJacksonStrategy implements SerDeStrategy{

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @Override
    public String serialize(Object object) throws SerializationException {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Serialization failed", e);
        }
    }

    @Override
    public <T> T deserialize(String serialized, Class<T> destinationClass) throws DeserializationException {
        try {
            return mapper.readValue(serialized, destinationClass);
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Deserialization failed for class " + destinationClass.getCanonicalName(), e);
        }
    }
}
