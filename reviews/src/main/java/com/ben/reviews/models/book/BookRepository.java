package com.ben.reviews.models.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>/*, CustomBookRepository*/ {

    Optional<Book> findById(Integer id);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByIdIn(List<Integer> userIds);

}
