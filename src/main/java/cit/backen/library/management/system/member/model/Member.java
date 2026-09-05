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

    @NotBlank
    private String NINNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Loan> loans  = new ArrayList<>();

    public Member(String NINNumber, String firstName, String lastName) {
        this.NINNumber = NINNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
