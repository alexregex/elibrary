package com.libproject.elibrary.service;

import com.libproject.elibrary.model.BookRating;
import com.libproject.elibrary.model.BookRatingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("bookRatingDtoService")
public class BookRatingDtoServiceImpl implements BookRatingDtoService {

    @Override
    public BookRatingDto createBookRatingDto(Collection<BookRating> bookRatings, String userLogin) {
        if (!bookRatings.isEmpty()) {
            int allVotes = Math.toIntExact(bookRatings.stream().count());
            double sum = bookRatings.stream().mapToInt(BookRating::getRating).sum();
            double rating = sum / allVotes;
            boolean isVoted = bookRatings.stream().map(BookRating::getUser).anyMatch(user -> user.getLogin().equals(userLogin));
            return new BookRatingDto(rating, allVotes, isVoted, userLogin, 0);
        }
        return new BookRatingDto((double) 1, 0, false, userLogin, 0);
    }
}
