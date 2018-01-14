package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.CommentDao;
import com.libproject.elibrary.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public Comment findById(int id) {
        return commentDao.findById(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentDao.findAllComments();
    }
}
