package org.fermented.dairy.post.queue.core.immutable;

import java.util.Arrays;
import java.util.Iterator;

import static org.fermented.dairy.post.queue.core.util.Objects.required;

/// Lightweight immutable array wrapper
///
/// @param <T> the type of the array elements
public record ImmutableArray<T>(T[] array) implements Iterable<T> {
    /// Creates an immutable array wrapper
    ///
    /// @param array the array to wrap
    public ImmutableArray {
        required(array);
        array = Arrays.copyOf(array, array.length);
    }

    /// Returns a copy of the underlying array, ensuring immutability by preventing
    /// external modifications to the original array.
    ///
    /// @return a copy of the internal array
    public T[] array() {
        return Arrays.copyOf(array, array.length);
    }

    /// Gets the element at the specified index
    ///
    /// @param index the index of the element to get
    /// @return the element at the specified index
    /// @throws IndexOutOfBoundsException if the index is out of bounds
    public T get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return array[index];
    }

    /// Gets the length of the array
    ///
    /// @return the length of the array
    public int length() {
        return array.length;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                final var value = array[index];
                index++;
                return value;
            }
        };
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    /// Equality tests based on array contents. Displays "unboxing" behavior such that if compared to an array of T, then the internal array is compared for equality.
    ///
    /// @param obj the object to test equality against
    /// @return true if the objects are equal, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj.getClass().isArray())
            return Arrays.equals(array, (Object[]) obj);
        if (getClass() != obj.getClass())
            return false;
        ImmutableArray<?> other = (ImmutableArray<?>) obj;
        return Arrays.equals(array, other.array);
    }

    @Override
    public String toString() {
        return "ImmutableArray{array=" + Arrays.toString(array) + '}';
    }
}
