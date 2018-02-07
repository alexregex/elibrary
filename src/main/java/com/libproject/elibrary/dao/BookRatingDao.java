package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.BookRating;

import java.util.Collection;

public interface BookRatingDao {

    BookRating findById(int id);

    void saveBookRating(BookRating bookRating);

    Collection<BookRating> findAllBookRatings();

    Collection <BookRating> findByCertainBookId(Book book);


}
