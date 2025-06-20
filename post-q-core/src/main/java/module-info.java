module post.q.core {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports org.fermented.dairy.post.queue.core.exceptions;
    exports org.fermented.dairy.post.queue.core.messages;
    exports org.fermented.dairy.post.queue.core.immutable;
    exports org.fermented.dairy.post.queue.core.serialization;
    exports org.fermented.dairy.post.queue.core.util;

    //Required for tests
    opens org.fermented.dairy.post.queue.core.serialization.internal to com.fasterxml.jackson.databind;
}