package com.libproject.elibrary.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*Redirect after authorization, based by role*/
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = String.valueOf(authentication.getAuthorities());

        session.setAttribute("username", ((UserDetails)principal).getUsername());
        session.setAttribute("authorities", role);

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        if (role.equalsIgnoreCase("[ROLE_USER]")) {
            httpServletResponse.sendRedirect("/books/all");
        } else {
            httpServletResponse.sendRedirect("/books/admin-booklist");
        }
    }
}
