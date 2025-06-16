package org.fermented.dairy.post.queue.core.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ImmutableArrayTest {

    @Test
    @DisplayName("Array contents equals array input")
    void arrayContentsEqualsArrayInput() {
        final String[] array = {"a", "b", "c", "e", "f", "g"};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(array[i], immutableArray.get(i));
        }
    }

    @Test
    @DisplayName("Iteration Matches Array Contents")
    void iterationMatchesArrayContents() {
        final String[] array = {"a", "b", "c", "e", "f", "g"};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        int i = 0;
        for(String s : immutableArray) {
            assertEquals(array[i++], s);
        }
    }

    @Test
    @DisplayName("Iterate over empty array")
    void iterateOverEmptyArray() {
        final String[] array = {};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        int i = 0;
        for(String _ : immutableArray) {
            i++;
        }
        assertEquals(0, i);
        assertEquals(0, immutableArray.length());
    }

    @Test
    @DisplayName("Negative Index")
    void negativeIndex() {
        final String[] array = {"a", "b", "c", "e", "f", "g"};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        assertThrows(IndexOutOfBoundsException.class, () -> immutableArray.get(-1));
    }

    @Test
    @DisplayName("Index Too High")
    void indexTooHigh() {
        final String[] array = {"a", "b", "c", "e", "f", "g"};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        assertThrows(IndexOutOfBoundsException.class, () -> immutableArray.get(6));
        assertThrows(IndexOutOfBoundsException.class, () -> immutableArray.get(7));
        assertThrows(IndexOutOfBoundsException.class, () -> immutableArray.get(immutableArray.length()));
    }

    @Test
    @DisplayName("Equality Tests")
    void equalityTests() {
        final String[] array = {"a", "b", "c", "e", "f", "g"};
        final String[] array2 = {"a", "b", "c", "e", "f", "g", "h"};
        final ImmutableArray<String> immutableArray = new ImmutableArray<>(array);
        final ImmutableArray<String> immutableArray2 = new ImmutableArray<>(array);
        final ImmutableArray<String> immutableArray3 = new ImmutableArray<>(array2);
        //noinspection SimplifiableAssertion,EqualsBetweenInconvertibleTypes
        assertAll("Equality checks, including commutative equality",
                () -> assertTrue(immutableArray.equals(immutableArray2), "immutableArray == immutableArray2"),
                () -> assertTrue(immutableArray2.equals(immutableArray), "immutableArray2 == immutableArray"),
                () -> assertFalse(immutableArray3.equals(immutableArray2), "immutableArray3 != immutableArray2"),
                () -> assertFalse(immutableArray.equals(immutableArray3), "immutableArray != immutableArray3"),
                () -> assertTrue(immutableArray.equals(array), "unboxing behaviour")
        );

        assertAll("Hash Test",
                () -> assertEquals(immutableArray.hashCode(), immutableArray2.hashCode()),
                () -> assertNotEquals(immutableArray.hashCode(), immutableArray3.hashCode())
        );
    }
}