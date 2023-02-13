package com.ben.reviews.book_library;

import com.ben.reviews.models.user_book.UserBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsResponse {
    List<UserBook> bookReviews;

}
