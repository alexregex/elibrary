package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.BookDao;
import com.libproject.elibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        Book updateBook = bookDao.findById(book.getId());

            updateBook.setId(book.getId());
            updateBook.setTitle(book.getTitle());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setPublishing(book.getPublishing());
            updateBook.setDate(book.getDate());
            updateBook.setDescription(book.getDescription());
            updateBook.setCover(book.getCover());
            updateBook.setLink(book.getLink());
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAllBooks();
    }

    @Override
    public void removeFile(String path) {
        File file = new File(path);

        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
