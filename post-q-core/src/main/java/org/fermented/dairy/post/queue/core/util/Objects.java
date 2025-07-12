package org.fermented.dairy.post.queue.core.util;

import org.fermented.dairy.post.queue.core.messages.InvalidMessageException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class Objects {

    /**
     * Returns the provided value if it is not null, otherwise returns a default value
     * supplied by the given {@code defaultSupplier}.
     *
     * @param value the value to be checked for null
     * @param defaultSupplier the supplier that provides a default value if {@code value} is null
     * @param <T> the type of the value
     * @return {@code value} if it is not null; otherwise a value provided by {@code defaultSupplier}
     */
    public static <T> T orDefault(final T value, final Supplier<T> defaultSupplier) {
        if (value == null) {
            return defaultSupplier.get();
        }
        return value;
    }

    /**
     * Ensures that the provided value is not null.
     * If the value is null, an {@code InvalidMessageException} is thrown.
     *
     * @param value the value to be checked for null
     * @param <T> the type of the value
     * @return the provided value if it is not null
     * @throws InvalidMessageException if the provided value is null
     */
    public static <T> T required(final T value) {
        if (value == null)
            throw new InvalidMessageException("Value cannot be null");
        return value;
    }

    /**
     * Ensures that the provided map value is not null.
     * If the map is null, an {@code InvalidMessageException} is thrown.
     * Returns an unmodifiable copy of the provided map.
     *
     * @param value the map to be checked for null
     * @param <T> the type of keys in the map
     * @param <U> the type of values in the map
     * @return an unmodifiable copy of the provided map
     * @throws InvalidMessageException if the provided map is null
     */
    public static <T, U> Map<T,U> required(final Map<T,U> value) {
        if (value == null)
            throw new InvalidMessageException("Value cannot be null");
        return Map.copyOf(value);
    }

    /**
     * Ensures that the provided list is not null.
     * If the list is null, an {@code InvalidMessageException} is thrown.
     * Returns an unmodifiable copy of the provided list.
     *
     * @param value the list to be checked for null
     * @param <T>   the type of elements in the list
     * @return an unmodifiable copy of the provided list
     * @throws InvalidMessageException if the provided list is null
     */
    public static <T> List<T> required(final List<T> value) {
        if (value == null)
            throw new InvalidMessageException("Value cannot be null");
        return List.copyOf(value);
    }

    /**
     * Ensures that the provided set is not null.
     * If the set is null, an {@code InvalidMessageException} is thrown.
     * Returns an unmodifiable copy of the provided set.
     *
     * @param value the set to be checked for null
     * @param <T> the type of elements in the set
     * @return an unmodifiable copy of the provided set
     * @throws InvalidMessageException if the provided set is null
     */
    public static <T> Set<T> required(final Set<T> value) {
        if (value == null)
            throw new InvalidMessageException("Value cannot be null");
        return Set.copyOf(value);
    }

    private Objects() {}
}
