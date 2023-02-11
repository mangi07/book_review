package com.ben.reviews.models.author;

import com.ben.reviews.models.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByName(String name);

}
