package cit.backen.library.management.system.loan.dto;

import cit.backen.library.management.system.loan.enums.Status;


public record LoanResponse(
        Integer period,
        Long memberId,
        Long bookId,
        Status status
) {}
