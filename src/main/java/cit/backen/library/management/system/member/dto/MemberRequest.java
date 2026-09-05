package cit.backen.library.management.system.member.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    @NotBlank(message = "NIN Number cannot be blank")
    @Size(min = 14, max = 14,message = "Nin Number must be characters ")
    private String ninNumber;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 4, max = 20, message = "First name must be between 4 to 20 characters")
    private String firstName;

    @NotBlank(message = "last name cannot be blank")
    @Size(min = 4, max = 20, message = "Last name must be between 4 to 20 characters")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "username must be between 4 to 50 characters")
    private String username;
}
