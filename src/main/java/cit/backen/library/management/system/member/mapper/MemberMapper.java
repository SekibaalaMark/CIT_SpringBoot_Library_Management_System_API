package cit.backen.library.management.system.member.mapper;

import cit.backen.library.management.system.member.dto.MemberRequest;
import cit.backen.library.management.system.member.model.Member;

public class MemberMapper {
    public Member memberRequestToModel(MemberRequest request){
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setNinNumber(request.getNinNumber());
        member.setUsername(request.getUsername());
        return member;
    }
}
