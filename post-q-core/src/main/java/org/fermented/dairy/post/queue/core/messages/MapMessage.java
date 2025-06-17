package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.messages.internal.MapMessageImpl;

import java.util.Map;

@SuppressWarnings("unused")
public sealed interface MapMessage extends Message permits MapMessageImpl {

    String getString(String key);

    Integer getInteger(String key);

    Long getLong(String key);

    Double getDouble(String key);

    Float getFloat(String key);

    Boolean getBoolean(String key);

    Map<String, String> body();
}
