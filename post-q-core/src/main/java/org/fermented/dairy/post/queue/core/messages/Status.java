package org.fermented.dairy.post.queue.core.messages;

/// Represents the various statuses that a message or operation can have in a system.
/// This enumeration is used to indicate the current state of a message or process as it moves
/// through different stages in a workflow or a system.
public enum Status {
    /// Indicates that the message or process is in a pending state, waiting to be processed.
    WAITING,
    /// Indicates that the message or process is currently being handled or executed.
    PROCESSING,
    /// Indicates that the message or process has encountered an error or failure.
    FAILED,
    /// Indicates that the message has been retried or resent after a previous delivery attempt.
    RESENT,
    /// Indicates that the message or process has been successfully completed.
    PROCESSED
}
