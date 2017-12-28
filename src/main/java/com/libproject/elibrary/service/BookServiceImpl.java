package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.BookDao;
import com.libproject.elibrary.model.Book;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    SessionFactory sessionFactory;

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
    public Collection<Book> searchByText(String searchText, boolean isByTitle, boolean isByDescription) {
        Collection<Book> resultSearch = new HashSet<>();
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
            fullTextSession.createIndexer().startAndWait();

            QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
                    .forEntity(Book.class).get();

            if(isByTitle) {
                org.apache.lucene.search.Query lucenceQuery = queryBuilder.keyword().fuzzy().onField("title").matching(searchText).createQuery();

                org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(lucenceQuery,
                        Book.class);

                resultSearch.addAll((HashSet<Book>)fullTextQuery.list());
            }

            if(isByDescription) {
                org.apache.lucene.search.Query lucenceQuery = queryBuilder.keyword().onField("description").matching(searchText).createQuery();

                org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(lucenceQuery,
                        Book.class);

                resultSearch.addAll((HashSet<Book>)fullTextQuery.list());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultSearch;
    }

    @Override
    public Collection<Book> findAllBooks() {
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
