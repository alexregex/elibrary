package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Book;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("BookDao")
public class BookDaoImpl extends AbstractDao<Integer,Book> implements BookDao {

    @Override
    public Book findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveBook(Book book) {
        persist(book);
    }

    @Override
    public void deleteBook(Book book) {
        delete(book);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Book> findAllBooks() {
        Criteria criteria = createEntityCriteria();
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
