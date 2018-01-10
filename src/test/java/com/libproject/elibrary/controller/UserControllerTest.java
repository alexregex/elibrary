package com.libproject.elibrary.controller;

import com.libproject.elibrary.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.libproject.elibrary.utils.SecurityUtils.userAdmin;
import static com.libproject.elibrary.utils.SecurityUtils.userJoe;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends AbstractIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    public void shouldAddUser() throws Exception {
        mockMvc.perform(post("/newuser").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("firstName", "John")
                                    .param("lastName", "Dou")
                                    .param("login", "dou")
                                    .param("password", "d1234567")
                                    .param("confirmPassword", "d1234567")
                                    .param("email", "johnd@gmail.com"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void shouldRemoveUser() throws Exception {
        Integer id = userService.findByLogin("dou").getId();
        mockMvc.perform(get("/delete-user-" + id.toString()).with(userAdmin()).with(csrf()))
                .andExpect(status().is(302));
    }

    @Test
    public void shouldEditUser() throws Exception {
        Integer id = userService.findByLogin("user").getId();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("u12345");
        mockMvc.perform(post("/edit-user-" + id.toString()).with(userAdmin()).with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("firstName", "Ivan")
                                    .param("lastName", "Ivanov")
                                    .param("login", "user")
                                    .param("password", password)
                                    .param("confirmPassword", password)
                                    .param("email", "ivan.ivanov@gmail.com")
                                    .param("userProfiles", "2"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin-userslist"));
    }

    @Test
    public void tryingRegisterWithSameLogin() throws Exception {
        mockMvc.perform(post("/newuser").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("firstName", "John")
                                    .param("lastName", "Dou")
                                    .param("login", "user")
                                    .param("password", "d1234567")
                                    .param("confirmPassword", "d1234567")
                                    .param("email", "johnd@gmail.com"))
                .andExpect(model().hasErrors())
                .andExpect(view().name("newUser"));
    }

    @Test
    public void shouldReturnErrorWhenPasswordsMismatch() throws Exception {
       mockMvc.perform(post("/newuser").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("firstName", "Bill")
                                    .param("lastName", "Gates")
                                    .param("login", "bill")
                                    .param("password", "b1234567")
                                    .param("confirmPassword", "b1235467")
                                    .param("email", "billg@mail.com"))
                .andExpect(model().hasErrors())
                .andExpect(view().name("newUser"));

    }

    @Test
    public void shouldReturnErrorWhenWrongEmail() throws Exception {
        mockMvc.perform(post("/newuser").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("firstName", "Bill")
                                    .param("lastName", "Gates")
                                    .param("login", "bill")
                                    .param("password", "b1234567")
                                    .param("confirmPassword", "b1234567")
                                    .param("email", "@mail"))
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(view().name("newUser"));
    }

    @Test
    public void shouldAccessDeniedAdminUserList() throws Exception {
        mockMvc.perform(get("/admin-userslist").with(userJoe()).with(csrf()))
                .andExpect(status().isForbidden());
    }

}