package cit.backen.library.management.system.book.dto;

import cit.backen.library.management.system.book.enums.Edition;
import cit.backen.library.management.system.book.enums.Status;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotBlank(message = "ISBN Cannot be blank")
    private  String isbn;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 10, max=200,message = "Title Cannot Exceed 200 Letters and must be at leat 10 characters")
    private String title;

    @NotNull(message = "Edition cannot be Null")
    private Edition edition;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 5, max=100,message = "Author Cannot Exceed 200 Letters and ust be at least 5 Characters")
    private String author;

    @NotNull(message = "Status cannot be Null")
    private Status status;
}
