package org.fermented.dairy.post.queue.core.serialization;

/// Defines a strategy for serializing and deserializing objects.
/// This interface provides methods for converting objects to and from a serialized format.
///
/// @param <S> the type of the serialized representation
@SuppressWarnings("unused")
public interface SerDeStrategy<S> {
    /// Serializes an object into the target format.
    ///
    /// @param object the object to serialize
    /// @return the serialized representation of the object
    /// @throws SerializationException if an error occurs during serialization
    S serialize(Object object) throws SerializationException;

    /// Deserializes data from the serialized format into an object of the specified class.
    ///
    /// @param serialized the serialized data to deserialize
    /// @param destinationClass the class to deserialize the data into
    /// @param <T> the type of the resulting object
    /// @return the deserialized object
    /// @throws DeserializationException if an error occurs during deserialization
    <T> T deserialize(S serialized, Class<T> destinationClass) throws DeserializationException;

}
