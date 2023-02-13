package com.ben.reviews.book_search.json_model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    String title;
    List<Author> authors;
    String notes; // to be mapped to DB "description"
    String publish_date;
    Cover cover;
    String isbn;

}
