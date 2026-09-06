package cit.backen.library.management.system.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberResponse(
        Long id,
        String ninNumber,
        String firstName,
        String lastName,
        String username
) {
}