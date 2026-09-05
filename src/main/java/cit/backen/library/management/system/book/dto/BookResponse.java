package cit.backen.library.management.system.book.dto;

import cit.backen.library.management.system.book.enums.Edition;
import cit.backen.library.management.system.book.enums.Status;
import lombok.Data;

public record BookResponse(Long id, String isbn, String title, String author , Edition edition, Status status) {}
