package com.libproject.elibrary.model;

public class BookRatingDto {

    private Double rating;

    private Integer votes;

    private Boolean isVoted;

    private String userLogin;

    private Integer bookId;

    public BookRatingDto() {}

    public BookRatingDto(Double rating, Integer votes, Boolean isUserVoted, String userLogin, Integer bookId) {
        this.rating = rating;
        this.votes = votes;
        this.isVoted = isUserVoted;
        this.userLogin = userLogin;
        this.bookId = bookId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

}
