package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Book;

import java.util.List;

public interface BookDao {

    Book findById(int id);

    void saveBook(Book book);

    void deleteBook(Book book);

    List<Book> findAllBooks();
}
