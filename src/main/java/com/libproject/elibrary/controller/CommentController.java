package com.libproject.elibrary.controller;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.Comment;
import com.libproject.elibrary.model.User;
import com.libproject.elibrary.service.BookService;
import com.libproject.elibrary.service.CommentService;
import com.libproject.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommentController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/books/book-{id}/comments/add", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveComment(@PathVariable("id") Integer id, @ModelAttribute("newComment") Comment comment) {
        Book book = bookService.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());

        Comment newComment = new Comment();
        newComment.setCommentText(comment.getCommentText());
        newComment.setBook(book);
        newComment.setUser(user);
        commentService.saveComment(newComment);

        return "redirect: /books/book-" + id;
    }
}
