package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.MapMessage;
import org.fermented.dairy.post.queue.core.messages.Message;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

import java.util.Map;

/// Specialized consumer interface for processing map messages.
/// This interface extends MessageConsumer and provides functionality for consuming
/// and processing messages that contain key-value pairs in a map structure.
public interface MapMessageConsumer extends MessageConsumer{

    @Override
    default void consume(final Message message) throws MessageConsumerException {
        final Map<String, String> body = switch (message){
            case final MapMessage mapMessage -> {
                try{
                    yield mapMessage.body();
                } catch (final DeserializationException dEx) {
                    throw MessageConsumerException.from(dEx);
                }
            }
            default -> throw new MessageConsumerException("Message is not a MapMessage");
        };
        process(body, message.metaData());
    }

    /// Processes the map message body along with its metadata.
    /// This method is called by the default implementation of consume() after
    /// the map message body has been successfully retrieved.
    ///
    /// @param message the map containing the message body as key-value pairs
    /// @param metaData the metadata associated with the message
    /// @throws MessageConsumerException if an error occurs during message processing
    void process(final Map<String,String> message, final Map<String, String> metaData) throws MessageConsumerException;
}
