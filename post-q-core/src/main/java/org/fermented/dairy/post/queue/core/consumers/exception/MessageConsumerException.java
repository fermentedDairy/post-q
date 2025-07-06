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

    public MessageConsumerException(final String message) {
        super(message);
    }

    public MessageConsumerException(final Throwable cause) {
        super(cause);
    }

    public MessageConsumerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /// Converts a [DeserializationException] into a [MessageConsumerException].
    /// This method is used to wrap deserialization errors encountered during message parsing.
    public static MessageConsumerException from(final DeserializationException deserializationException) {
        return new MessageConsumerException("Deserialisation failed", deserializationException);
    }
}
