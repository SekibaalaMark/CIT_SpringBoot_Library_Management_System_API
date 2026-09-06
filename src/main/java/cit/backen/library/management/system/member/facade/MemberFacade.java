package cit.backen.library.management.system.member.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.member.MemberNotFoundException;
import cit.backen.library.management.system.exceptions.member.MemberWithUsernameAlreadyExistsException;
import cit.backen.library.management.system.member.dto.MemberPartialUpdateRequest;
import cit.backen.library.management.system.member.dto.MemberRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.mapper.MemberMapper;
import cit.backen.library.management.system.member.model.Member;
import cit.backen.library.management.system.member.repository.MemberRepository;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


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

        if(memberRepository.existsByUsername(request.getUsername())){
            throw new MemberWithUsernameAlreadyExistsException(request.getUsername());
        }

        MemberResponse memberResponse = memberMapper.memberModelToResponse(memberRepository.save(member));
        return new ApiResponse<MemberResponse>("SUCCESS","Member added successfully",memberResponse);

    }


    public ApiResponse<PageResponse<MemberResponse>> getAllMembers(int page, int pageSize){
        int zeroBasedPage = Math.max(0,page-1);

        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize, Sort.by("username").ascending());
        Page<Member> membersPage = memberRepository.findAll(pageable);
        List<MemberResponse> memberResponseList = membersPage.getContent()
                .stream()
                .map(memberMapper::memberModelToResponse)
                .toList();
        PageResponse<MemberResponse> pageResponse = new PageResponse<>(
                memberResponseList,
                page,
                pageSize,
                membersPage.getTotalElements(),
                membersPage.getTotalPages(),
                membersPage.isLast()
        );

        return new ApiResponse<>("SUCCESS","Page of members",pageResponse);
    }


    public ApiResponse<MemberResponse> updateMemberFully(Long id ,MemberRequest request){
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException("id:"+ id));
        if(memberRepository.existsByUsername(request.getUsername())){
            throw new MemberWithUsernameAlreadyExistsException(request.getUsername());
        }

        member.setUsername(request.getUsername());
        member.setNinNumber(request.getNinNumber());
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());

        MemberResponse memberResponse = memberMapper.memberModelToResponse(memberRepository.save(member));
        return new ApiResponse<>("SUCCESS","member updated successfully",memberResponse);
    }



    public ApiResponse<MemberResponse> updateMemberPartially(Long id , MemberPartialUpdateRequest request){
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException("id: "+ id));
        if(memberRepository.existsByUsername(request.getUsername())){
            throw new MemberWithUsernameAlreadyExistsException(request.getUsername());
        }

        if(request.getFirstName() != null){
            member.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            member.setLastName(request.getLastName());
        }
        if(request.getUsername() != null){
            member.setUsername(request.getUsername());
        }
        if(request.getNinNumber() != null){
            member.setNinNumber(request.getNinNumber());
        }
        MemberResponse memberResponse = memberMapper.memberModelToResponse(memberRepository.save(member));
        return new ApiResponse<>("SUCCESS","Member updated successfully",memberResponse);
    }
}
