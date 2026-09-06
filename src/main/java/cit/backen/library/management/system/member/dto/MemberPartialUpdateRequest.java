package cit.backen.library.management.system.member.dto;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPartialUpdateRequest {
    private String ninNumber;
    private String firstName;
    private String lastName;
    private String username;
}
