package org.fermented.dairy.post.queue.core.serialization;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String message) {
        super(message);
    }

    public DeserializationException(String message, Throwable cause) {
    super(message, cause);
  }
}
