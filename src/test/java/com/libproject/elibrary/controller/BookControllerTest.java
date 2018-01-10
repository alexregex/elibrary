package com.libproject.elibrary.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import java.util.HashMap;
import java.util.Map;

import static com.libproject.elibrary.utils.SecurityUtils.userAdmin;
import static com.libproject.elibrary.utils.SecurityUtils.userJoe;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BookControllerTest extends AbstractIntegrationTest {

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