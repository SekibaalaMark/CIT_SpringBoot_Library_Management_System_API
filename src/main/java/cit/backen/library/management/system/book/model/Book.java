package cit.backen.library.management.system.book.model;

import cit.backen.library.management.system.book.enums.Edition;
import cit.backen.library.management.system.book.enums.Status;
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
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ISBN Cannot be blank")
    private String Isbn;

    @NotBlank(message = "title cannot be blank")
    @Size(max=200,message = "Title Cannot Exceed 200 Letters")
    private String title;

    @NotNull(message = "edition is required")
    private Edition edition;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotNull(message = "Status is required")
    private Status status;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    public Book(String isbn, String title, Edition edition, String author, Status status) {
        Isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.author = author;
        this.status = status;
    }
}
