package com.libproject.elibrary.service;

import com.libproject.elibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BookService {

    Book findById(int id);

    void saveBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void removeFile(String path);

    Collection<Book> findAllBooks();
}
