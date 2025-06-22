package org.fermented.dairy.post.queue.core.messages;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/// Represents a generic message that can be sent, received, and processed
/// within a messaging system. This interface serves as a base for defining
/// different message types such as JSON, Map, and Object messages.
/// Implementations of this interface are required to provide methods for
/// accessing core message metadata, such as message ID, timestamps,
/// delivery details, and optional properties for correlation and reply queues.
@SuppressWarnings("unused")
public sealed interface Message permits JsonMessage, MapMessage, ObjectMessage {

    /// Retrieves the unique identifier of the message.
    ///
    /// @return a `UUID` representing the unique identifier of the message.
    UUID messageId();

    /// Retrieves the timestamp indicating when the message was sent.
    ///
    /// @return a `ZonedDateTime` representing the sent timestamp of the message.
    ZonedDateTime sentTimeStamp();

    /// Retrieves the correlation identifier associated with the message, if available.
    /// The correlation ID is commonly used to link related messages together in a
    /// messaging system, enabling tracking and coordination of message exchanges.
    ///
    /// @return an `Optional<UUID>` representing the correlation identifier
    ///         of the message, or an empty `Optional` if no correlation ID is present.
    Optional<UUID> correlationId();

    /// Retrieves the target destination queue to which the message is intended to be sent.
    ///
    /// @return a `String` representing the name of the destination queue.
    String destinationQueue();

    /// Retrieves the queue name where reply messages should be sent, if specified.
    /// This field is used to indicate a target destination for reply messages
    /// in a messaging workflow.
    ///
    /// @return an `Optional<String>` containing the reply-to queue name
    ///         if defined, or an empty `Optional` if no reply-to queue is specified.
    Optional<String> replyToQueue();

    /// Indicates whether this message has been redelivered.
    /// A redelivered message typically indicates that a previous delivery attempt
    /// was unsuccessful and the messaging system is attempting to deliver it again.
    ///
    /// @return `true` if the message has been redelivered, `false` otherwise.
    boolean redelivered();

    /// Retrieves the number of times this message has been redelivered.
    /// This count starts at 0 for the initial delivery and increments
    /// with each redelivery attempt.
    ///
    /// @return an integer representing the redelivery count.
    int redeliveryCount();

    Map<String, String> metaData();
}
