package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.Message;

public interface MessageConsumer {
    void consume(final Message message) throws MessageConsumerException;
    String destination();
}

