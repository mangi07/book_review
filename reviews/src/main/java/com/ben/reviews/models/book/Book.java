package com.ben.reviews.models.book;

import com.ben.reviews.models.author.Author;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity(name = "book")
public class Book {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    private String isbn;
    private String author;
    private String title;
    private String description;
    private Integer publication_year;
    private String cover_image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author_obj;
}
