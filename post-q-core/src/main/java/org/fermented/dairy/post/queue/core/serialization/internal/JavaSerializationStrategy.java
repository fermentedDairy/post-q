package org.fermented.dairy.post.queue.core.serialization.internal;

import org.fermented.dairy.post.queue.core.serialization.DeserializationException;
import org.fermented.dairy.post.queue.core.serialization.SerDeStrategy;
import org.fermented.dairy.post.queue.core.serialization.SerializationException;

import java.io.*;

public class JavaSerializationStrategy implements SerDeStrategy<byte[]> {

    public static final JavaSerializationStrategy INSTANCE = new JavaSerializationStrategy();

    private JavaSerializationStrategy() {}

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        try(
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)
        ){
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Could Not Serialize", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] serialized, Class<T> destinationClass) throws DeserializationException {

        try(
                final ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
                final ObjectInputStream objectInputStream = new ObjectInputStream(bais)
        ){
            var value = objectInputStream.readObject();
            if (destinationClass.isInstance(value)) {
                return destinationClass.cast(value);
            }
            throw new DeserializationException("Could not deserialize. Expected " + destinationClass.getCanonicalName() + " but got " + value.getClass().getCanonicalName());
        } catch (IOException | ClassNotFoundException e) {
            throw new DeserializationException("Could not deserialize", e);
        }
    }
}
