package com.libproject.elibrary.service;

import com.libproject.elibrary.model.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(int id);

    void saveComment(Comment comment);

    List<Comment> findAllComments();
}
