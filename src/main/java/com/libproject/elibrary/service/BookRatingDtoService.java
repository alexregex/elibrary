package com.libproject.elibrary.service;

import com.libproject.elibrary.model.BookRating;
import com.libproject.elibrary.model.BookRatingDto;

import java.util.Collection;

public interface BookRatingDtoService {

    BookRatingDto createBookRatingDto(Collection<BookRating> bookRatings, String userLogin);
}
