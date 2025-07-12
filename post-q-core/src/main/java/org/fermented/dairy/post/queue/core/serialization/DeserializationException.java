package org.fermented.dairy.post.queue.core.serialization;

/// Exception thrown when an error occurs during the deserialization process.
/// This runtime exception indicates that serialized data could not be properly
/// converted back to an object due to various issues such as invalid format,
/// missing data, type mismatches, or other deserialization failures.
public class DeserializationException extends RuntimeException {
    /// Constructs a new deserialization exception with the specified detail message.
    ///
    /// @param message the detail message explaining the reason for the exception
    public DeserializationException(final String message) {
        super(message);
    }

    /// Constructs a new deserialization exception with the specified detail message and cause.
    ///
    /// @param message the detail message explaining the reason for the exception
    /// @param cause the underlying cause of this exception
    public DeserializationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
