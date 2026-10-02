package cit.backen.library.management.system.member.controller;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.member.dto.MemberRegistrationRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class MemberAuthController {


//    @PostMapping
//    public ResponseEntity<ApiResponse<MemberResponse>> addMember(@Valid @RequestBody MemberRegistrationRequest request){
//        ApiResponse<MemberResponse> apiResponse = memberService.addMember(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
//    }

    @GetMapping
    public String hello(){
        return "Hello, welcome";
    }
}
