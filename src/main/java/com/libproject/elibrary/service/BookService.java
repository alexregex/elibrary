package com.libproject.elibrary.service;

import com.libproject.elibrary.model.Book;

import java.util.Collection;

public interface BookService {

    Book findById(int id);

    void saveBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void removeFile(String path);

    Collection<Book> searchByText(String searchText, boolean isByTitle, boolean isByDescription);

    Collection<Book> findAllBooks();
}
