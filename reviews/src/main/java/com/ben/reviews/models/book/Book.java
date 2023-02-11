package com.ben.reviews.models.book;

import com.ben.reviews.models.author.Author;
import jakarta.persistence.*;
import lombok.Builder;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Entity(name = "book")
public class Book {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    private String author;
    private String title;
    private String description;
    private Date publication_date;
    private String cover_image; // TODO: find out whether there might be a better type for this
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author_obj; // TODO: make this of type Author, instead

    public Book(){}
    public Book(Integer id,
                String title,
                String author,
                String description,
                Date publication_date,
                String cover_image,
                Author author_obj
                ){
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publication_date = publication_date;
        this.cover_image = cover_image;
        this.author_obj = author_obj;
    }
}
