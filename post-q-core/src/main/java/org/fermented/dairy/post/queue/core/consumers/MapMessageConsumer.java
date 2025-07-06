package org.fermented.dairy.post.queue.core.consumers;

import org.fermented.dairy.post.queue.core.consumers.exception.MessageConsumerException;
import org.fermented.dairy.post.queue.core.messages.MapMessage;
import org.fermented.dairy.post.queue.core.messages.Message;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;

import java.util.Map;

public interface MapMessageConsumer extends MessageConsumer{

    @Override
    default void consume(final Message message) throws MessageConsumerException {
        final Map<String, String> body = switch (message){
            case final MapMessage mapMessage -> {
                try{
                    yield mapMessage.body();
                } catch (final DeserializationException dEx) {
                    throw MessageConsumerException.from(dEx);
                }
            }
            default -> throw new MessageConsumerException("Message is not a MapMessage");
        };
        process(body, message.metaData());
    }

    void process(final Map<String,String> message, final Map<String, String> metaData) throws MessageConsumerException;
}
