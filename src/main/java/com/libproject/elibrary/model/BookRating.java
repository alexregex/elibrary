package com.libproject.elibrary.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOK_RATING")
public class BookRating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookRating that = (BookRating) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(rating, that.rating)
                .append(user, that.user)
                .append(book, that.book)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(rating)
                .append(user)
                .append(book)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BookRating{" +
                "id=" + id +
                ", rating=" + rating +
                ", user=" + user.getId() +
                ", book=" + book.getId() +
                '}';
    }
}
