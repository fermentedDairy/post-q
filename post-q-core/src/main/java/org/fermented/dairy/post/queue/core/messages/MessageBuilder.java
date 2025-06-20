package org.fermented.dairy.post.queue.core.messages;

import org.fermented.dairy.post.queue.core.immutable.ImmutableArray;
import org.fermented.dairy.post.queue.core.messages.internal.JsonMessageImpl;
import org.fermented.dairy.post.queue.core.messages.internal.MapMessageImpl;
import org.fermented.dairy.post.queue.core.messages.internal.ObjectMessageImpl;
import org.fermented.dairy.post.queue.core.serialization.JsonSerDeStrategy;
import org.fermented.dairy.post.queue.core.serialization.internal.DefaultJacksonStrategy;
import org.fermented.dairy.post.queue.core.serialization.internal.JavaSerializationStrategy;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MessageBuilder {

    //<editor-fold desc="MessageBuilderInt">
    @SuppressWarnings("unused")
    private abstract static class MessageBuilderInt {
     protected UUID messageId;
     protected ZonedDateTime sentTimeStamp = ZonedDateTime.now();
     protected UUID correlationId;
     protected String destinationQueue;
     protected String replyToQueue;
     protected boolean redelivered = false;
     protected int redeliveryCount = 0;

     public UUID correlationId() {
         return correlationId;
     }

     public MessageBuilderInt correlationId(UUID correlationId) {
         this.correlationId = correlationId;
         return this;
     }

     public String destinationQueue() {
         return destinationQueue;
     }

     public MessageBuilderInt destinationQueue(String destinationQueue) {
         this.destinationQueue = destinationQueue;
         return this;
     }

     public UUID messageId() {
         return messageId;
     }

     public MessageBuilderInt messageId(UUID messageId) {
         this.messageId = messageId;
         return this;
     }

     public boolean redelivered() {
         return redelivered;
     }

     public MessageBuilderInt redelivered(boolean redelivered) {
         this.redelivered = redelivered;
         return this;
     }

     public int redeliveryCount() {
         return redeliveryCount;
     }

     public MessageBuilderInt redeliveryCount(int redeliveryCount) {
         this.redeliveryCount = redeliveryCount;
         return this;
     }

     public String replyToQueue() {
         return replyToQueue;
     }

     public MessageBuilderInt replyToQueue(String replyToQueue) {
         this.replyToQueue = replyToQueue;
         return this;
     }

     public ZonedDateTime sentTimeStamp() {
         return sentTimeStamp;
     }

     public MessageBuilderInt sentTimeStamp(ZonedDateTime sentTimeStamp) {
         this.sentTimeStamp = sentTimeStamp;
         return this;
     }

     public abstract Message build();
    }
    //</editor-fold>

    //<editor-fold desc="JsonMessageBuilder">
    @SuppressWarnings("unused")
    public static class JsonMessageBuilder<T> extends MessageBuilderInt {

        private JsonSerDeStrategy serDeStrategy = DefaultJacksonStrategy.INSTANCE;
        private T body;

        private JsonMessageBuilder() {}

        public T body() {
            return body;
        }

        public JsonMessageBuilder<T> body(T body) {
            this.body = body;
            return this;
        }

        public JsonSerDeStrategy serDeStrategy() {
            return serDeStrategy;
        }

        public JsonMessageBuilder<T> serDeStrategy(JsonSerDeStrategy serDeStrategy) {
            this.serDeStrategy = serDeStrategy;
            return this;
        }

        public Message build(){
            return new JsonMessageImpl(
                    messageId,
                    sentTimeStamp,
                    Optional.ofNullable(correlationId),
                    destinationQueue,
                    Optional.ofNullable(replyToQueue),
                    redelivered,
                    redeliveryCount,
                    serDeStrategy,
                    body
            );
        }
    }
    //</editor-fold>

    //<editor-fold desc="ObjectMessageBuilder">
    @SuppressWarnings("unused")
    public static class ObjectMessageBuilder<T> extends MessageBuilderInt {

        private T body;

        public T body() {
            return body;
        }

        public ObjectMessageBuilder<T> body(T body) {
            this.body = body;
            return this;
        }

        private ObjectMessageBuilder() {}

        @Override
        public Message build() {

            return new ObjectMessageImpl(
                    messageId,
                    sentTimeStamp,
                    Optional.ofNullable(correlationId),
                    destinationQueue,
                    Optional.ofNullable(replyToQueue),
                    redelivered,
                    redeliveryCount,
                    new ImmutableArray<>(box(JavaSerializationStrategy.INSTANCE.serialize(body)))
            );
        }
    }
    //</editor-fold>

    //<editor-fold desc="MapMessageBuilder">
    public static class MapMessageBuilder<T> extends MessageBuilderInt {

        private Map<String, String> body;

        private MapMessageBuilder() {}

        public Map<String, String> body() {
            return body;
        }

        public MapMessageBuilder<T> body(Map<String, String> body) {
            this.body = body;
            return this;
        }

        @Override
        public Message build() {

            return new MapMessageImpl(
                    messageId,
                    sentTimeStamp,
                    Optional.ofNullable(correlationId),
                    destinationQueue,
                    Optional.ofNullable(replyToQueue),
                    redelivered,
                    redeliveryCount,
                    body
            );
        }
    }
    //</editor-fold>

    /// Creates a new instance of `JsonMessageBuilder`.
    /// This method provides a fluent interface for constructing JSON messages,
    /// allowing customization of the message's body and serialization/deserialization strategy.
    ///
    /// @param <T> The type of the message body.
    /// @return A `JsonMessageBuilder` instance for constructing JSON messages.
    public static <T> JsonMessageBuilder<T> json() {
        return new JsonMessageBuilder<T>();
    }

    /// Creates a new instance of `ObjectMessageBuilder`.
    /// This method provides a fluent interface for constructing messages
    /// containing serialized Java objects as their payload. It allows for
    /// customization and configuration of the message's metadata and body.
    ///
    /// @param <T> The type of the object that will be serialized and set as the message body.
    /// @return An `ObjectMessageBuilder` instance used for constructing object-based messages.
    public static <T> ObjectMessageBuilder<T> object() {
        return new ObjectMessageBuilder<T>();
    }

    /// Creates a new instance of `MapMessageBuilder`.
    /// This method provides a fluent interface for constructing messages containing
    /// key-value pairs as their body. It allows customization and configuration
    /// of the message's metadata and body before construction.
    ///
    /// @param <T> The type of the message body.
    /// @return A `MapMessageBuilder` instance used for constructing map-based messages.
    public static <T> MapMessageBuilder<T> map() {
        return new MapMessageBuilder<T>();
    }

    private static Byte[] box(byte[] bytes) {
        Byte[] boxedArray = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            boxedArray[i] = bytes[i]; // Auto-unboxing
        }
        return boxedArray;
    }
}
