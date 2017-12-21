package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Book;

import java.util.Collection;
import java.util.Set;

public interface BookDao {

    Book findById(int id);

    void saveBook(Book book);

    void deleteBook(Book book);

    Collection<Book> findAllBooks();
}
