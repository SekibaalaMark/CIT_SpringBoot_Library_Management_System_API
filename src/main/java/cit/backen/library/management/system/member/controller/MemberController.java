package cit.backen.library.management.system.member.controller;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.member.dto.MemberPartialUpdateRequest;
import cit.backen.library.management.system.member.dto.MemberRegistrationRequest;
import cit.backen.library.management.system.member.dto.MemberResponse;
import cit.backen.library.management.system.member.service.MemberService;
import cit.backen.library.management.system.page.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@EnableMethodSecurity
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<PageResponse<MemberResponse>>> getAllMembers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ApiResponse<PageResponse<MemberResponse>> apiResponse = memberService.getAllMembers(page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberById(@PathVariable Long id){
        ApiResponse<MemberResponse> apiResponse = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberResponse>> getCurrentUser(Authentication authentication){
        ApiResponse<MemberResponse> apiResponse = memberService.getMemberByUsername(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('LIBRARIAN') or #username == authentication.name")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberByUsername(@PathVariable String username){
        ApiResponse<MemberResponse> apiResponse = memberService.getMemberByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<MemberResponse>> updateMemberFully(@PathVariable Long id ,@Valid @RequestBody MemberRegistrationRequest request){
        ApiResponse<MemberResponse> apiResponse = memberService.updateMemberFully(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<MemberResponse>> updateMemberPartially(@PathVariable Long id , @RequestBody MemberPartialUpdateRequest request){
        ApiResponse<MemberResponse> apiResponse = memberService.updateMemberPartially(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<Object>> deleteMemberById(@PathVariable Long id){
        ApiResponse<Object> apiResponse = memberService.deleteMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
