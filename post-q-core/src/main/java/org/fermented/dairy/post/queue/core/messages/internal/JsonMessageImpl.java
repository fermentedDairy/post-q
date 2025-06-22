package org.fermented.dairy.post.queue.core.messages.internal;

import org.fermented.dairy.post.queue.core.messages.JsonMessage;
import org.fermented.dairy.post.queue.core.serialization.JsonSerDeStrategy;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.fermented.dairy.post.queue.core.util.Objects.orDefault;
import static org.fermented.dairy.post.queue.core.util.Objects.required;

public record JsonMessageImpl(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        JsonSerDeStrategy serDeStrategy,
        String body,
        Map<String, String> metaData
) implements JsonMessage {

    public JsonMessageImpl(
            UUID messageId,
            ZonedDateTime sentTimeStamp,
            Optional<UUID> correlationId,
            String destinationQueue,
            Optional<String> replyToQueue,
            boolean redelivered,
            int redeliveryCount,
            JsonSerDeStrategy serDeStrategy,
            Object body,
            Map<String, String> metaData
    ) {
        this(
                messageId,
                sentTimeStamp,
                correlationId,
                destinationQueue,
                replyToQueue,
                redelivered,
                redeliveryCount,
                serDeStrategy,
                serDeStrategy.serialize(body),
                metaData
        );
    }

    public JsonMessageImpl {
        messageId = orDefault(messageId, UUID::randomUUID);
        sentTimeStamp = orDefault(sentTimeStamp, ZonedDateTime::now);
        correlationId = orDefault(correlationId, Optional::empty);
        destinationQueue = required(destinationQueue);
        replyToQueue = orDefault(replyToQueue, Optional::empty);
        body = required(body);
        metaData = metaData.isEmpty() ? Map.of() : Map.copyOf(metaData); //Make immutable
    }

    @Override
    public <T> T body(final Class<T> destinationClass) {
        return serDeStrategy.deserialize(body, destinationClass);
    }
}
