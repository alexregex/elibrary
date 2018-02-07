package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.BookRatingDao;
import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.BookRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("bookRatingService")
@Transactional
public class BookRatingServiceImpl implements BookRatingService {

    @Autowired
    BookRatingDao bookRatingDao;

    @Override
    public BookRating findById(int id) {
        return bookRatingDao.findById(id);
    }

    @Override
    public void saveBookRating(BookRating bookRating) {
        bookRatingDao.saveBookRating(bookRating);
    }

    @Override
    public Collection<BookRating> findAllBookRatings() {
        return bookRatingDao.findAllBookRatings();
    }

    @Override
    public Collection<BookRating> findByCertainBookId(Book book) {
        return bookRatingDao.findByCertainBookId(book);
    }
}
