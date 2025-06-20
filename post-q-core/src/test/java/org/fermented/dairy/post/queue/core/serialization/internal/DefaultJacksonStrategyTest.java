package org.fermented.dairy.post.queue.core.serialization.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fermented.dairy.post.queue.core.serialization.DeserializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DefaultJacksonStrategyTest {


    private final DefaultJacksonStrategy javaSerializationStrategy = DefaultJacksonStrategy.INSTANCE;

    @ParameterizedTest(name = "{0} could not be serialised or deserialised")
    @MethodSource("objectsProvider")
    @DisplayName("Serialise an object, deserialise the byte array and confirm that the result equals the original")
    void SerDeTest(@SuppressWarnings("rawtypes") Class klass, Object value) {
        var serialised = javaSerializationStrategy.serialize(value);
        @SuppressWarnings("unchecked") var deserialised = javaSerializationStrategy.deserialize(serialised, klass);
        assertEquals(value, deserialised);
    }

    @Test
    @DisplayName("Attempt to serialize null")
    void SerDeTestNull() {
        var serialised = javaSerializationStrategy.serialize(null);
        var deserialised = javaSerializationStrategy.deserialize(serialised, SerDeTestClass.class);
        assertNull(deserialised);
    }

    public static Stream<Arguments> objectsProvider() {
        var random = new Random();
        return Stream.of(
                Arguments.of(SerDeTestClass.class, new SerDeTestClass("nameClass", random.nextLong())),
                Arguments.of(SerDeTestRecord.class, new SerDeTestRecord("nameRecord", random.nextLong()))
        );
    }

    private record SerDeTestRecord(String name, long num) implements Serializable {}

    private static class SerDeTestClass implements Serializable {

        private final String name;
        private final long num;

        @Serial
        private static final long serialVersionUID = 1L;

        public SerDeTestClass(@JsonProperty("name") String name, @JsonProperty("num") long num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            SerDeTestClass that = (SerDeTestClass) o;
            return num == that.num && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, num);
        }

        @JsonProperty("name")
        public String name() {
            return name;
        }

        @JsonProperty("num")
        public long num() {
            return num;
        }
    }

}