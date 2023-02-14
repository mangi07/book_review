package com.ben.reviews.models.user_library_book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@SecondaryTable(name = "user_book", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class UserLibraryBook {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    Integer id;


}


