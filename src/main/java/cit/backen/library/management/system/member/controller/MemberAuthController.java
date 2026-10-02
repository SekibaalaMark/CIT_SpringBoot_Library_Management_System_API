package cit.backen.library.management.system.member.controller;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.member.MemberWithUsernameAlreadyExistsException;
import cit.backen.library.management.system.member.dto.MemberRegistrationRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.facade.MemberFacade;
import cit.backen.library.management.system.member.model.Member;
import cit.backen.library.management.system.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberAuthController {
    private final MemberFacade memberFacade;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MemberResponse>> register(@Valid @RequestBody MemberRegistrationRequest request){
        ApiResponse<MemberResponse> apiResponse = memberFacade.addMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public String hello(){
        return "Hello, welcome";
    }
}
