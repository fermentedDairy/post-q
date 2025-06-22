package org.fermented.dairy.post.queue.core.messages.internal;

import org.fermented.dairy.post.queue.core.messages.ObjectMessage;
import org.fermented.dairy.post.queue.core.immutable.ImmutableArray;
import org.fermented.dairy.post.queue.core.serialization.internal.JavaSerializationStrategy;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.fermented.dairy.post.queue.core.util.Objects.orDefault;
import static org.fermented.dairy.post.queue.core.util.Objects.required;

public record ObjectMessageImpl(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        ImmutableArray<Byte> body,
        Map<String, String> metaData
) implements ObjectMessage {

    public ObjectMessageImpl {
        messageId = orDefault(messageId, UUID::randomUUID);
        sentTimeStamp = orDefault(sentTimeStamp, ZonedDateTime::now);
        correlationId = orDefault(correlationId, Optional::empty);
        destinationQueue = required(destinationQueue);
        replyToQueue = orDefault(replyToQueue, Optional::empty);
        body = required(body);
        metaData = metaData.isEmpty() ? Map.of() : Map.copyOf(metaData);
    }

    @Override
    public ImmutableArray<Byte> rawBody() {
        return body;
    }

    @Override
    public <T> T body(Class<T> destinationClass){
        return JavaSerializationStrategy.INSTANCE.deserialize(toPrimitive(body.array()), destinationClass);
    }

    private static byte[] toPrimitive(Byte[] wrapperArray) {
        byte[] primitiveArray = new byte[wrapperArray.length];
        for (int i = 0; i < wrapperArray.length; i++) {
            primitiveArray[i] = wrapperArray[i]; // Auto-unboxing
        }
        return primitiveArray;
    }
}
