package com.libproject.elibrary.service;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.BookRating;

import java.util.Collection;

public interface BookRatingService {

    BookRating findById(int id);

    void saveBookRating(BookRating bookRating);

    Collection<BookRating> findAllBookRatings();

    Collection<BookRating> findByCertainBookId(Book book);
}
