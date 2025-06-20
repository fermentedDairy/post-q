package org.fermented.dairy.post.queue.core.serialization;

@SuppressWarnings("unused")
public interface SerDeStrategy<S> {
    S serialize(Object object) throws SerializationException;
    <T> T deserialize(S serialized, Class<T> destinationClass) throws DeserializationException;

}
