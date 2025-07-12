package org.fermented.dairy.post.queue.core.serialization.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;
import org.fermented.dairy.post.queue.core.serialization.JsonSerDeStrategy;
import org.fermented.dairy.post.queue.core.serialization.SerializationException;

public final class DefaultJacksonStrategy implements JsonSerDeStrategy {

    private static final ObjectMapper mapper;
    public static final DefaultJacksonStrategy INSTANCE = new DefaultJacksonStrategy();

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    private DefaultJacksonStrategy() {}

    @Override
    public String serialize(final Object object) throws SerializationException {
        try {
            return mapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new SerializationException("Serialization failed", e);
        }
    }

    @Override
    public <T> T deserialize(final String serialized, final Class<T> destinationClass) throws DeserializationException {
        try {
            return mapper.readValue(serialized, destinationClass);
        } catch (final JsonProcessingException e) {
            throw new DeserializationException("Deserialization failed for class " + destinationClass.getCanonicalName(), e);
        }
    }
}
