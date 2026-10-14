package cit.backen.library.management.system.member.model;


import cit.backen.library.management.system.loan.model.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Size(min = 4, max = 50, message = "username must be between 4 to 20 characters")
    private String username;

    @NotBlank(message = "Password Cannot be blank")
    @Size(min=8,message = "Password must be at least 8 characters")
    private String password;

    @OneToMany(mappedBy = "member",orphanRemoval = true)
    private List<Loan> loans  = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull(message = "User Role  is required")
    private Role role = Role.MEMBER;

    public Member(String ninNumber, String firstName, String lastName,String username,String password,Role role) {
        this.ninNumber = ninNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
