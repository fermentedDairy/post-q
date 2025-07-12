package org.fermented.dairy.post.queue.core.serialization;

/// Exception thrown when an error occurs during the serialization process.
/// This runtime exception indicates that an object could not be properly
/// converted to its serialized form due to various issues such as
/// invalid object state, unsupported types, or other serialization failures.
public class SerializationException extends RuntimeException {

    /// Constructs a new serialization exception with the specified detail message.
    ///
    /// @param message the detail message explaining the reason for the exception
    public SerializationException(final String message) {
        super(message);
    }

    /// Constructs a new serialization exception with the specified detail message and cause.
    ///
    /// @param message the detail message explaining the reason for the exception
    /// @param cause the underlying cause of this exception
    public SerializationException(final String message, final Throwable cause) {
      super(message, cause);
    }
}
