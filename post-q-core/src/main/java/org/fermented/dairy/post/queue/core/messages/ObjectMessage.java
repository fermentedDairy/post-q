package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.immutable.ImmutableArray;
import org.fermented.dairy.post.queue.core.messages.internal.ObjectMessageImpl;

/// Represents a message containing a serialized Java object as its payload.
/// This interface extends the base Message interface, adding methods specific
/// to handling binary object data that can be deserialized back into Java objects.
/// ObjectMessage supports the transmission of serializable Java objects within
/// the messaging system, providing type-safe deserialization capabilities.
@SuppressWarnings("unused")
public sealed interface ObjectMessage extends Message permits ObjectMessageImpl {

    /// Retrieves the raw binary content of the message as an immutable array of bytes.
    /// This method provides access to the underlying binary representation of the serialized object.
    ///
    /// @return an `ImmutableArray<Byte>` containing the raw binary data of the message
    ImmutableArray<Byte> rawBody();

    /// Deserializes the binary content of the message into an object of the specified type.
    /// This method handles the conversion from the binary representation back to a Java object.
    ///
    /// @param <T> the target type parameter for the deserialized object
    /// @param destinationClass the Class object representing the expected type of the deserialized object
    /// @return an instance of the specified class containing the deserialized data
    /// @throws org.fermented.dairy.post.queue.core.serialization.DeserializationException if the binary data cannot be deserialized
    ///         into the specified type or if any other deserialization errors occur
    <T> T body(Class<T> destinationClass);

    /// Retrieves the binary content of the message as an immutable array of bytes.
    /// This method is an alias for [#rawBody()] and provides the binary representation
    /// of the serialized object.
    ///
    /// @return an `ImmutableArray<Byte>` containing the binary data of the message
    ImmutableArray<Byte> body();
}
