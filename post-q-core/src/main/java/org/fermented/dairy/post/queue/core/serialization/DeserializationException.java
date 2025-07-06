package org.fermented.dairy.post.queue.core.serialization;

public class DeserializationException extends RuntimeException {
    public DeserializationException(final String message) {
        super(message);
    }

    public DeserializationException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
