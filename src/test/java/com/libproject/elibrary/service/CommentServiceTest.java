package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.CommentDao;
import com.libproject.elibrary.model.Comment;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    @Mock
    CommentDao commentDao;

    @InjectMocks
    CommentServiceImpl commentService;

    @Captor
    private ArgumentCaptor<Comment> commentArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCommentById() {
        Integer commentId = 1;

        Comment comment = new Comment();
        comment.setId(1);

        when(commentDao.findById(commentId)).thenReturn(comment);

        Comment retrievedComment = commentDao.findById(commentId);

        assertThat(retrievedComment, is(equalTo(comment)));

        verify(commentDao, times(1)).findById(commentId);
    }

    @Test
    public void addNewComment() {
       commentService.saveComment(new Comment());

       Mockito.verify(commentDao).saveComment(commentArgumentCaptor.capture());

       assertThat(commentArgumentCaptor.getValue(), Matchers.is(notNullValue()));
    }

    @Test
    public void listOfComments() {
        List<Comment> comments = new ArrayList<>();
        when(commentDao.findAllComments()).thenReturn(comments);

        assertThat(comments, notNullValue());
    }
}