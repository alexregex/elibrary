package com.libproject.elibrary.utils;

import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class SecurityUtils {

    public static RequestPostProcessor userJoe() {
        return user("joe").roles("USER");
    }

    public static RequestPostProcessor userAdmin() {
        return user("admin").roles("ADMIN");
    }
}
