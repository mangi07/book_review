package com.ben.reviews.models.book;

import com.ben.reviews.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findById(Integer id);
    Optional<Book> findByIsbn(String isbn);

}
