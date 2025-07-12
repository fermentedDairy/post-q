package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.JsonMessage;
import org.fermented.dairy.post.queue.core.messages.Message;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

import java.util.Map;

/// Specialized consumer interface for processing JSON messages.
/// This interface extends MessageConsumer and provides functionality for consuming
/// and processing messages that contain JSON data that can be deserialized to a specific type.
///
/// @param <T> the type to which the JSON data will be deserialized
public interface JsonConsumer<T> extends MessageConsumer {

    @Override
    default void consume(final Message message) throws MessageConsumerException {
        final T body = switch (message){
            case final JsonMessage jsonMessage -> {
                try {
                    yield jsonMessage.body(getMessageType());
                } catch (final DeserializationException dEx) {
                    throw MessageConsumerException.from(dEx);
                }
            }
            default -> throw new MessageConsumerException("Message is not a JsonMessage");
        };
        process(body, message.metaData());
    }

    /// Processes the deserialized JSON message body along with its metadata.
    /// This method is called by the default implementation of consume() after
    /// the JSON message body has been successfully deserialized.
    ///
    /// @param message the deserialized message body
    /// @param metaData the metadata associated with the message
    /// @throws MessageConsumerException if an error occurs during message processing
    void process(final T message, final Map<String, String> metaData) throws MessageConsumerException;

    /// Returns the class object representing the type to which JSON data should be deserialized.
    /// This method is used for type-safe deserialization of JSON message bodies.
    ///
    /// @return the Class object for type T
    Class<T> getMessageType();
}
