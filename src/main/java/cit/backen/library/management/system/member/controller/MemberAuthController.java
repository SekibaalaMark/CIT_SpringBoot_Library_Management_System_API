package cit.backen.library.management.system.member.controller;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.member.dto.MemberRegistrationRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.dto.UserLoginRequest;
import cit.backen.library.management.system.member.facade.MemberFacade;
import cit.backen.library.management.system.member.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberAuthController {
    private final MemberFacade memberFacade;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MemberResponse>> register(@Valid @RequestBody MemberRegistrationRequest request){
        ApiResponse<MemberResponse> apiResponse = memberFacade.addMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody UserLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.username(), request.password()));

        String token = jwtService.generateToken(authentication.getName());
        ApiResponse<String> apiResponse = new ApiResponse<>(
                "SUCCESS", "Login successful for " + authentication.getName(), token);
        return ResponseEntity.ok(apiResponse);
    }
}
