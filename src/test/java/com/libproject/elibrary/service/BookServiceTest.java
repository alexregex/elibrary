package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.BookDao;
import com.libproject.elibrary.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    BookDao bookDao;

    @InjectMocks
    BookServiceImpl bookService;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBookById() {
        Integer bookId = 1;

        Book book = new Book();
        book.setId(1);

        when(bookDao.findById(bookId)).thenReturn(book);

        Book retrievedBook = bookService.findById(bookId);

        assertThat(retrievedBook, is(equalTo(book)));

        verify(bookDao, times(1)).findById(bookId);
    }

    @Test
    public void getNullIfBookNotExists() {
        Integer bookId = 1;

        Book retrievedBook = bookService.findById(bookId);

        assertThat(retrievedBook, is(equalTo(null)));
    }

    @Test
    public void addNewBook() {
        bookService.saveBook(new Book());

        verify(bookDao).saveBook(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue(), is(notNullValue()));
    }

    @Test
    public void deleteBook() {
        Integer bookId = 1;

        Book book = new Book();

        when(bookDao.findById(bookId)).thenReturn(book);

        bookService.deleteBook(book);

        verify(bookDao, times(1)).deleteBook(book);
    }

    @Test
    public void listOfBook() {
        Set<Book> books = new HashSet<>();

        when(bookDao.findAllBooks()).thenReturn(books);

        assertThat(books, is(notNullValue()));
    }
}