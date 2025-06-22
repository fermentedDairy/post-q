package org.fermented.dairy.post.queue.core.messages.internal;

import org.fermented.dairy.post.queue.core.exceptions.MessageRuntimeException;
import org.fermented.dairy.post.queue.core.messages.MapMessage;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.fermented.dairy.post.queue.core.util.Objects.orDefault;
import static org.fermented.dairy.post.queue.core.util.Objects.required;

@SuppressWarnings("unused")
public record MapMessageImpl(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        Map<String, String> body,
        Map<String, String> metaData
) implements MapMessage {

    public MapMessageImpl {
        messageId = orDefault(messageId, UUID::randomUUID);
        sentTimeStamp = orDefault(sentTimeStamp, ZonedDateTime::now);
        correlationId = orDefault(correlationId, Optional::empty);
        destinationQueue = required(destinationQueue);
        replyToQueue = orDefault(replyToQueue, Optional::empty);
        body = required(body);
        final Map<String, String> tmpMetaData = orDefault(metaData, Map::of);
        metaData = tmpMetaData.isEmpty() ? Map.of() : Map.copyOf(tmpMetaData); //Make immutable
    }

    //<editor-fold desc="Value fetchers">
    @Override
    public String getString(final String key) {
        final Object value = body.get(key);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public Integer getInteger(final String key) {
        final String value = body.get(key);
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new MessageRuntimeException("Value for key " + key + " is not an integer", e);
        }
    }

    @Override
    public Long getLong(final String key) {
        final String value = body.get(key);
        if (value == null) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new MessageRuntimeException("Value for key " + key + " is not a long", e);
        }
    }

    @Override
    public Double getDouble(final String key) {
        final String value = body.get(key);
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new MessageRuntimeException("Value for key " + key + " is not a double", e);
        }
    }

    @Override
    public Float getFloat(final String key) {
        final String value = body.get(key);
        if (value == null) {
            return null;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new MessageRuntimeException("Value for key " + key + " is not a float", e);
        }
    }

    @Override
    public Boolean getBoolean(final String key) {
        final String value = body.get(key);
        if (value == null) {
            return null;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            throw new MessageRuntimeException("Value for key " + key + " is not a boolean", e);
        }
    }
    //</editor-fold>

    @Override
    public Map<String, String> body() {
        return Map.copyOf(body);
    }
}
