package org.fermented.dairy.post.queue.core.messages;

import exceptions.MessageRuntimeException;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
public record MapMessage(
        UUID messageId,
        ZonedDateTime sentTimeStamp,
        Optional<UUID> correlationId,
        String destinationQueue,
        Optional<String> replyToQueue,
        boolean redelivered,
        int redeliveryCount,
        Map<String, String> body
) implements Message {

    public String getString(final String key) {
        final Object value = body.get(key);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

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
}
