package cit.backen.library.management.system.member.dto;



public record MemberResponse(
        Long id,
        String ninNumber,
        String firstName,
        String lastName,
        String username
) {}