package org.fermented.dairy.post.queue.core.messages;

/// Exception thrown to indicate that an invalid or unacceptable message
/// has been encountered during processing.
///
/// This exception typically signals an issue with an expected value
/// being invalid, such as a null or improperly structured message within
/// the message-handling system. For example, it may be used to enforce
/// non-null requirements or validate the integrity of a message.
///
/// By extending `RuntimeException`, this exception allows for
/// unchecked propagation, making it suitable for runtime validation scenarios.
public class InvalidMessageException extends RuntimeException {

    /// Constructs a new `InvalidMessageException` with the specified detail message.
    ///
    /// @param message the detail message explaining the reason for the exception
    public InvalidMessageException(String message) {
        super(message);
    }
}
