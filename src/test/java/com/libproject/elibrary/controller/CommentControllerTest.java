package com.libproject.elibrary.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

public class CommentControllerTest extends AbstractIntegrationTest {

    @Test
    @WithUserDetails("admin")
    public void shouldAddComment() throws Exception {
        mockMvc.perform(post("/books/book-1/comments/add").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("commentText", "This is test comment"))
                .andExpect(redirectedUrlPattern("**/books/book-1"));
    }
}