package cit.backen.library.management.system.member.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.member.dto.MemberRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.mapper.MemberMapper;
import cit.backen.library.management.system.member.model.Member;
import cit.backen.library.management.system.member.repository.MemberRepository;
import org.springframework.stereotype.Component;


@Component
public class MemberFacade {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberFacade(MemberMapper memberMapper, MemberRepository memberRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
    }

    public ApiResponse<MemberResponse> addMember(MemberRequest request){
        Member member = memberMapper.memberRequestToModel(request);
        MemberResponse memberResponse = memberMapper.memberModelToResponse(memberRepository.save(member));
        return new ApiResponse<MemberResponse>("SUCCESS","Member added successfully",memberResponse);
    }
}
