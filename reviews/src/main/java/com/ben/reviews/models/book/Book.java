package com.ben.reviews.models.book;

import com.ben.reviews.models.author.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String publication_date;
    private String cover_image;
    //TODO: cannot get this field populated
    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author_obj;
}
