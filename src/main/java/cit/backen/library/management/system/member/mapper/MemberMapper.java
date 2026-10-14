package cit.backen.library.management.system.member.mapper;

import cit.backen.library.management.system.member.dto.MemberRegistrationRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member memberRegistrationRequestToModel(MemberRegistrationRequest request){
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setNinNumber(request.getNinNumber());
        member.setUsername(request.getUsername());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        return member;
    }

    public MemberResponse memberModelToResponse (Member member){
        return new MemberResponse(
                member.getId(),
                member.getNinNumber(),
                member.getFirstName(),
                member.getLastName(),
                member.getUsername()
        );
    }
}
