package com.ben.reviews.models.user_book;

import com.ben.reviews.book_library.ReviewsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

      Optional<UserBook> findByUserIdAndBookId(Integer userId, Integer bookId);
      //To be used to find all reviews for a book
      List<UserBook> findByReviewIsNotNullAndBookIdEquals(Integer bookID);
      List<UserBook> findByUserId(Integer userId);

}
