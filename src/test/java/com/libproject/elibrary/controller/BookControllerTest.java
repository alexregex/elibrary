package com.libproject.elibrary.controller;

import com.libproject.elibrary.config.WebApplicationContextConfig;
import com.libproject.elibrary.security.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.*;
import javax.transaction.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static com.libproject.elibrary.utils.SecurityUtils.userAdmin;
import static com.libproject.elibrary.utils.SecurityUtils.userJoe;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplicationContextConfig.class, SecurityConfiguration.class})
@WebAppConfiguration
public class BookControllerTest {

    @Autowired
    WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldReturnBooks() throws Exception {
        mockMvc.perform(get("/books/all"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("books"));
    }

    @Test
    public void shouldReturnBookById() throws Exception {
        mockMvc.perform(get("/books/book-1"))
               .andExpect(status().isOk())
               .andExpect(view().name("book"))
               .andExpect(model().attributeExists("comments"));
    }

    @Test
    public void shouldReturnBookListIfAdmin() throws Exception {
        mockMvc.perform(get("/books/admin-booklist").with(userAdmin()))
                .andExpect(status().isOk());
    }

    @Test
    public void tryingRemoveBookByUser() throws Exception {
        mockMvc.perform(get("/books/delete-book-5").with(userJoe()).with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldCreateNewBookPageIfAdmin()  throws Exception {
        mockMvc.perform(get("/books/add").with(userAdmin()))
               .andExpect(status().isOk())
               .andExpect(view().name("newBook"))
               .andExpect(model().attributeExists("newBook"));
    }

    @Test
    public void shouldDenyAccessToNewBookIfNotAdmin() throws Exception {
        mockMvc.perform(get("/books/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("**/login"));

        mockMvc.perform(get("/books/add").with(userJoe()))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/books/add").with(userJoe()).with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test //? MultipartFile
    public void tryingAddNewBook() throws Exception {
        MockMultipartFile coverFile = new MockMultipartFile("test_image_file", "This is test image file".getBytes());
        MockMultipartFile bookFile = new MockMultipartFile("test_book_file", "This is test image book".getBytes());


        Map<String, String> contentTypeParams = new HashMap<>();
        contentTypeParams.put("id", "8");
        contentTypeParams.put("title", "this_is_test_title");
        contentTypeParams.put("author", "this_is_test_author");
        contentTypeParams.put("publishing", "this_is_test_publishing");
        contentTypeParams.put("description", "this_is_test_description");

        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

        mockMvc.perform(post("/books/add")
                .with(userAdmin())
                        .with(csrf())
                        .content(coverFile.getBytes())
                        .content(bookFile.getBytes())
                        .contentType(mediaType))
                .andDo(print())
                .andExpect(status().is(400));

    }
}