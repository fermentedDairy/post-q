package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.Message;
import org.fermented.dairy.post.queue.core.messages.ObjectMessage;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

import java.util.Map;

public interface ObjectMessageConsumer<T> extends MessageConsumer {

    @Override
    default void consume(final Message message) throws MessageConsumerException {
        final T body = switch (message){
            case final ObjectMessage objectMessage -> {
                try {
                    yield objectMessage.body(getMessageType());
                } catch (final DeserializationException dEx) {
                    throw MessageConsumerException.from(dEx);
                }
            }
            default -> throw new MessageConsumerException("Message is not a ObjectMessage");
        };
        process(body, message.metaData());
    }

    Class<T> getMessageType();

    void process(final T message, final Map<String, String> metaData) throws MessageConsumerException;
}
