package cit.backen.library.management.system.loan.dto;

import cit.backen.library.management.system.loan.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Loan period cannot be null")
    private Long period;

    @NotNull(message = "member id is required")
    private Long memberId;

    @NotNull(message = "book id is required")
    private Long bookId;
}
