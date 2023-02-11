package com.ben.reviews.models.author;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Entity(name = "author")
public class Author {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String bio;
}
