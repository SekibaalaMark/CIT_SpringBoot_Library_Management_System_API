package cit.backen.library.management.system.member.service;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.member.MemberNotFoundException;
import cit.backen.library.management.system.member.dto.MemberRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.facade.MemberFacade;
import cit.backen.library.management.system.member.mapper.MemberMapper;
import cit.backen.library.management.system.member.model.Member;
import cit.backen.library.management.system.member.repository.MemberRepository;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFacade memberFacade;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberFacade memberFacade, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberFacade = memberFacade;
        this.memberMapper = memberMapper;
    }

    public ApiResponse<MemberResponse> addMember(MemberRequest request){
        return memberFacade.addMember(request);
    }

    public ApiResponse<PageResponse<MemberResponse>> getAllMembers(int page,int pageSize){
        return memberFacade.getAllMembers(page,pageSize);
    }


    public ApiResponse<MemberResponse> getMemberById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException("id: "+id));
        MemberResponse memberResponse = memberMapper.memberModelToResponse(member);
        return new ApiResponse<>("SUCCESS","Member returned successfully",memberResponse);
    }

    public ApiResponse<MemberResponse> getMemberByUsername(String username){
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberNotFoundException("username: " + username));
        MemberResponse memberResponse = memberMapper.memberModelToResponse(member);
        return new ApiResponse<>("SUCCESS","Member returned successfully",memberResponse);
    }
}
