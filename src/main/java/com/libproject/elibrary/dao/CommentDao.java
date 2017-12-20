package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.Comment;

import java.util.List;

public interface CommentDao {

    Comment findById(int key);

    void saveComment(Comment comment);

    List<Comment> findAllComments();
}
