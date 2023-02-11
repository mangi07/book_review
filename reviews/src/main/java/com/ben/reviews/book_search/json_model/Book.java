package com.ben.reviews.book_search.json_model;

import java.util.List;

public class Book {
    //ApiBookInner apiBookInner;
    String title;
    List<Author> authors;
    String notes; // to be mapped to DB "description"
    String publish_date;
    Cover cover;

}
