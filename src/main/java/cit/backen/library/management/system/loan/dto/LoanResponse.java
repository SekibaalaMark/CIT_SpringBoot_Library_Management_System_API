package cit.backen.library.management.system.loan.dto;

import cit.backen.library.management.system.loan.enums.Status;

import java.time.LocalDate;


public record LoanResponse(
        Long id,
        LocalDate dateDue,
        Long memberId,
        Long bookId,
        Status status
) {}
