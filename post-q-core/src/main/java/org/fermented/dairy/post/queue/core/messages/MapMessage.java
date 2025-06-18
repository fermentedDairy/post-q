package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.messages.internal.MapMessageImpl;

import java.util.Map;

/// Represents a specialized type of message where the body consists of key-value pairs
/// stored in a map. This interface defines methods for retrieving values from the map
/// in various data types such as strings, integers, longs, doubles, floats, and booleans.
/// Implementations of this interface handle the logic of retrieving and managing these
/// values, facilitating flexible message processing in messaging systems.
/// It extends the generic `Message` interface, inheriting functionality for common
/// message metadata such as identifiers, timestamps, and delivery details.
@SuppressWarnings("unused")
public sealed interface MapMessage extends Message permits MapMessageImpl {

    /// Retrieves the String value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned
    /// @return the String value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    String getString(String key);

    /// Retrieves the Integer value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned as an Integer
    /// @return the Integer value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    /// @throws org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException if the value cannot be parsed as an Integer
    Integer getInteger(String key);

    /// Retrieves the Long value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned as a Long
    /// @return the Long value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    /// @throws org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException if the value cannot be parsed as a Long
    Long getLong(String key);

    /// Retrieves the Double value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned as a Double
    /// @return the Double value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    /// @throws org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException if the value cannot be parsed as a Double
    Double getDouble(String key);

    /// Retrieves the Float value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned as a Float
    /// @return the Float value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    /// @throws org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException if the value cannot be parsed as a Float
    Float getFloat(String key);

    /// Retrieves the Boolean value associated with the specified key from the message body.
    ///
    /// @param key the key whose associated value is to be returned as a Boolean
    /// @return the Boolean value to which the specified key is mapped, or `null`
    ///         if the map contains no mapping for the key
    /// @throws org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException if the value cannot be parsed as a Boolean
    Boolean getBoolean(String key);

    /// Returns an immutable copy of the message body as a map of key-value pairs.
    ///
    /// @return an unmodifiable [Map] containing all key-value pairs in the message body
    Map<String, String> body();
}
