package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Comment;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("CommentDao")
public class CommentDaoImpl extends AbstractDao<Integer,Comment> implements CommentDao{
    @Override
    public Comment findById(int key) {
        return getByKey(key);
    }

    @Override
    public void saveComment(Comment comment) {
        persist(comment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> findAllComments() {
        Criteria criteria = createEntityCriteria();
        return (List<Comment>) criteria.list();
    }
}
