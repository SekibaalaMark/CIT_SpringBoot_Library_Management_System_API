package cit.backen.library.management.system.messaging;

import java.time.Instant;
import java.util.UUID;

public record LoanCreatedEvent(
        UUID eventId,
        String eventType,
        Instant occurredAt,
        LoanCreatedData data) {
}