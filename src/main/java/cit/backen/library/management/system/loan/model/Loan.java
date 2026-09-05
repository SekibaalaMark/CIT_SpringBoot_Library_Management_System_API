package cit.backen.library.management.system.loan.model;


import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.loan.enums.Status;
import cit.backen.library.management.system.member.model.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;



@Data
@Setter
@Getter
@NoArgsConstructor
@Table(name = "loans")
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateDue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Loan Status is required")
    private Status status;

    public Loan(LocalDate dateDue, Status status, Member member, Book book) {
        this.dateDue = dateDue;
        this.status = status;
        this.member = member;
        this.book = book;
    }
}
