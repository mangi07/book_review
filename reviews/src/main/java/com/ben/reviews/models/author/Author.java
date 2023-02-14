package com.ben.reviews.models.author;

import com.ben.reviews.models.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "author")
public class Author {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String bio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author_obj")
    private List<Book> books;
}
