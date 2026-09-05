package cit.backen.library.management.system.member.service;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.member.dto.MemberRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.facade.MemberFacade;
import cit.backen.library.management.system.member.repository.MemberRepository;
import org.springframework.stereotype.Service;



@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFacade memberFacade;

    public MemberService(MemberRepository memberRepository, MemberFacade memberFacade) {
        this.memberRepository = memberRepository;
        this.memberFacade = memberFacade;
    }

    public ApiResponse<MemberResponse> addMember(MemberRequest request){
        return memberFacade.addMember(request);
    }
}
