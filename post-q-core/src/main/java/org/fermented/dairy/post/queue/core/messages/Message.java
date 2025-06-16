package org.fermented.dairy.post.queue.core.messages;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
public sealed interface Message permits JsonMessage, MapMessage, ObjectMessage {

    UUID messageId();
    ZonedDateTime sentTimeStamp();
    Optional<UUID> correlationId();
    String destinationQueue();
    Optional<String> replyToQueue();
    boolean redelivered();
    int redeliveryCount();

}
