package org.fermented.dairy.post.queue.core.consumers.exception;

import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

/// Represents an exception that is thrown when a failure occurs during the
/// consumption of a message within a messaging system. This exception can
/// encapsulate specific errors related to deserialization, processing failures,
/// or other issues encountered when consuming a `Message`.
/// This exception is intended to communicate errors that prevent a message
/// from being successfully processed. It allows for the inclusion of detailed
/// error messages and/or underlying root causes through optional chaining
/// of `Throwable` instances.
public class MessageConsumerException extends Exception {

    /// Constructs a new MessageConsumerException with the specified detail message.
    ///
    /// @param message the detail message (which is saved for later retrieval by the getMessage() method)
    public MessageConsumerException(final String message) {
        super(message);
    }

    /// Constructs a new MessageConsumerException with the specified cause.
    ///
    /// @param cause the cause (which is saved for later retrieval by the getCause() method)
    public MessageConsumerException(final Throwable cause) {
        super(cause);
    }

    /// Constructs a new MessageConsumerException with the specified detail message and cause.
    ///
    /// @param message the detail message (which is saved for later retrieval by the getMessage() method)
    /// @param cause the cause (which is saved for later retrieval by the getCause() method)
    public MessageConsumerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /// Converts a [DeserializationException] into a [MessageConsumerException].
    /// This method is used to wrap deserialization errors encountered during message parsing.
    public static MessageConsumerException from(final DeserializationException deserializationException) {
        return new MessageConsumerException("Deserialisation failed", deserializationException);
    }
}
