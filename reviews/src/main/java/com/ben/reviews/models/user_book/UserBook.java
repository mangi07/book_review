package com.ben.reviews.models.user_book;

import com.ben.reviews.models.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "user_book")
public class UserBook{

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "book_id")
    private Integer bookId;
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "book_id", insertable = false, updatable = false)
    //private Book book;
    private String review;
    private Integer rating;
}
