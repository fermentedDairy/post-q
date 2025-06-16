package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.util.ImmutableArray;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public record ObjectMessage(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        ImmutableArray<Byte> body
) implements Message{
}
