package cit.backen.library.management.system.messaging;

import java.time.LocalDate;

public record LoanCreatedData(Long loanId, Long memberId, Long bookId, LocalDate dueDate) {}
