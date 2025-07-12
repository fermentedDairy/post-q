package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.Message;
import org.fermented.dairy.post.queue.core.messages.ObjectMessage;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

import java.util.Map;

/// Specialized consumer interface for processing object messages.
/// This interface extends MessageConsumer and provides functionality for consuming
/// and processing messages that contain serialized objects of a specific type.
///
/// @param <T> the type of object contained in the messages this consumer processes
public interface ObjectMessageConsumer<T> extends MessageConsumer {

    @Override
    default void consume(final Message message) throws MessageConsumerException {
        final T body = switch (message){
            case final ObjectMessage objectMessage -> {
                try {
                    yield objectMessage.body(getMessageType());
                } catch (final DeserializationException dEx) {
                    throw MessageConsumerException.from(dEx);
                }
            }
            default -> throw new MessageConsumerException("Message is not a ObjectMessage");
        };
        process(body, message.metaData());
    }

    /// Returns the class object representing the type of messages this consumer processes.
    /// This method is used for type-safe deserialization of message bodies.
    ///
    /// @return the Class object for type T
    Class<T> getMessageType();

    /// Processes the deserialized message body along with its metadata.
    /// This method is called by the default implementation of consume() after
    /// the message body has been successfully deserialized.
    ///
    /// @param message the deserialized message body
    /// @param metaData the metadata associated with the message
    /// @throws MessageConsumerException if an error occurs during message processing
    void process(final T message, final Map<String, String> metaData) throws MessageConsumerException;
}
