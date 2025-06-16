package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.serialization.SerDeStrategy;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public record JsonMessage(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        SerDeStrategy serDeStrategy,
        String body
) implements Message{

    public <T> T body(final Class<T> destinationClass) {
        return serDeStrategy.deserialize(body, destinationClass);
    }
}
