package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.Message;

/// Defines a consumer for processing messages.
/// This interface provides methods for consuming messages and identifying the destination
/// where the consumer receives messages from.
public interface MessageConsumer {
    /// Processes a message received from a message queue.
    ///
    /// @param message the message to be consumed and processed
    /// @throws MessageConsumerException if an error occurs during message consumption
    void consume(final Message message) throws MessageConsumerException;

    /// Returns the destination name (e.g., queue or topic) from which this consumer receives messages.
    ///
    /// @return a string representing the destination name
    String destination();
}
