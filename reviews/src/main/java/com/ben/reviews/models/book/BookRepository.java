package com.ben.reviews.models.book;

import com.ben.reviews.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository {

    Optional<User> findById(Integer id);

}
