package com.libproject.elibrary.controller;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.BookRating;
import com.libproject.elibrary.model.BookRatingDto;
import com.libproject.elibrary.service.BookRatingDtoService;
import com.libproject.elibrary.service.BookRatingService;
import com.libproject.elibrary.service.BookService;
import com.libproject.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRatingController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRatingService bookRatingService;

    @Autowired
    BookRatingDtoService bookRatingDtoService;

    @RequestMapping(value = "/rating/{book_id}/{user_login}", method = RequestMethod.GET)
    public ResponseEntity<BookRatingDto> allBookRatings(@PathVariable("book_id") int bookId, @PathVariable("user_login") String userLogin) {
        Book book = bookService.findById(bookId);
        BookRatingDto bookRatingDto = bookRatingDtoService.createBookRatingDto(bookRatingService.findByCertainBookId(book), userLogin);
        return new ResponseEntity<>(bookRatingDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    public ResponseEntity<Void> saveBookRating(@RequestBody BookRatingDto newBookRatingDto) {

        BookRating bookRating = new BookRating();
        bookRating.setRating(newBookRatingDto.getRating().intValue());
        bookRating.setUser(userService.findByLogin(newBookRatingDto.getUserLogin()));
        bookRating.setBook(bookService.findById(newBookRatingDto.getBookId()));

        bookRatingService.saveBookRating(bookRating);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
