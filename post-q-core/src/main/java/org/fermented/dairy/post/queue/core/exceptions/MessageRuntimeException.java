package org.fermented.dairy.post.queue.core.exceptions;

/// Runtime exception thrown during message processing operations when unexpected
/// errors occur that cannot be recovered from normally.
///
/// This exception is typically used when encountering data conversion issues,
/// validation failures, or other runtime problems that prevent normal message
/// handling.
public class MessageRuntimeException extends RuntimeException {

    /// Constructs a new message runtime exception with the specified detail message.
    ///
    /// @param message the detail message explaining the reason for the exception
    public MessageRuntimeException(String message) {
        super(message);
    }

    /// Constructs a new message runtime exception with the specified detail message and cause.
    ///
    /// @param message the detail message explaining the reason for the exception
    /// @param cause the underlying cause of this exception
    public MessageRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
