package com.ben.reviews.book_library;

import com.ben.reviews.models.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {

    Book book;
    Boolean inLibrary;
}
