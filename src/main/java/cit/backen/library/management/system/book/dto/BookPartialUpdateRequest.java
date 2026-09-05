package cit.backen.library.management.system.book.dto;


import cit.backen.library.management.system.book.enums.Edition;
import cit.backen.library.management.system.book.enums.Status;

public record BookPartialUpdateRequest(
        String isbn,
        String title,
        Edition edition,
        String author,
        Status status
) {
}
