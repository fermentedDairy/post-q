package org.fermented.dairy.post.queue.core.serialization;

@SuppressWarnings("unused")
public interface SerDeStrategy {
    String serialize(Object object) throws SerializationException;
    <T> T deserialize(String serialized, Class<T> destinationClass) throws DeserializationException;

}
