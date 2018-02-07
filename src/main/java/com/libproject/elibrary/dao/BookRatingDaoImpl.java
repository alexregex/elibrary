package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.BookRating;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("BookRatingDao")
public class BookRatingDaoImpl extends AbstractDao<Integer,BookRating> implements BookRatingDao {
    @Override
    public BookRating findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveBookRating(BookRating bookRating) {
        persist(bookRating);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<BookRating> findAllBookRatings() {
        Criteria criteria = createEntityCriteria();
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<BookRating> findByCertainBookId(Book book) {
        Criteria criteria = createEntityCriteria();

        return criteria.add(Restrictions.eq("book", book)).list();
    }
}
