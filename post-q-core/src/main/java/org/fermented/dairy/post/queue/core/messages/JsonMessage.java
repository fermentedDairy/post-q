package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.messages.internal.JsonMessageImpl;
import org.fermented.dairy.post.queue.core.serialization.SerDeStrategy;

/// Represents a message containing JSON data that can be serialized
/// and deserialized using a specified `SerDeStrategy`. The interface
/// extends the generic `Message` interface and provides additional
/// methods specifically for working with JSON message bodies.
/// Implementations of this interface are responsible for handling JSON
/// serialization and deserialization of message content.
@SuppressWarnings("unused")
public sealed interface JsonMessage extends Message permits JsonMessageImpl {

    /// Deserializes the JSON body of the message into an object of the specified type.
    ///
    /// @param <T> The target type to deserialize the message body into
    /// @param destinationClass The class object representing the target type
    /// @return An instance of the specified type containing the deserialized message data
    /// @throws org.fermented.dairy.post.queue.core.serialization.DeserializationException if the body cannot be deserialized to the specified type
    <T> T body(Class<T> destinationClass);

    /// Retrieves the serialization/deserialization strategy used for this message.
    /// This strategy is responsible for converting between JSON strings and Java objects.
    ///
    /// @return the `SerDeStrategy` implementation used by this message
    SerDeStrategy serDeStrategy();

    /// Retrieves the raw JSON string content of this message.
    ///
    /// @return a `String` containing the JSON representation of the message body
    String body();
}
