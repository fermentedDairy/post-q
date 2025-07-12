package org.fermented.dairy.post.queue.core.serialization;

/// Specialization of the SerDeStrategy interface for JSON serialization.
/// This interface defines a strategy for serializing and deserializing objects to and from JSON format.
/// The serialized representation is a String containing JSON data.
public interface JsonSerDeStrategy extends SerDeStrategy<String>{
}
