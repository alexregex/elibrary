package com.libproject.elibrary.controller;

import com.libproject.elibrary.model.User;
import com.libproject.elibrary.model.UserProfile;
import com.libproject.elibrary.service.UserProfileService;
import com.libproject.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("roles")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @RequestMapping(value = "/admin-userslist", method = RequestMethod.GET)
    public String userList(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAllUsers());
        return "usersList";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "newUser";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "newUser";
        }

        if (! user.getPassword().equals(user.getConfirmPassword())) {
            FieldError loginError = new FieldError("user", "confirmPassword", messageSource.getMessage("non.confirm.password", new String[]{user.getLogin()}, Locale.getDefault()));
            result.addError(loginError);
            return "newUser";
        }

        if (userService.isUserLoginUnique(user.getId(), user.getLogin())) {
            FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login", new String[]{user.getLogin()}, Locale.getDefault()));
            result.addError(loginError);
            return "newUser";
        }

        user.setUserProfiles(userProfileService.findAll()
                .stream()
                .filter(userProfile -> userProfile.getType().equalsIgnoreCase("USER"))
                .collect(Collectors.toSet()));

        userService.saveUser(user);
        persistentTokenBasedRememberMeServices.logout(request,
                response,
                SecurityContextHolder.getContext().getAuthentication());
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }

    @RequestMapping(value = "/delete-user-{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {
        userService.removeUser(userService.findById(id));
        return "redirect:admin-userslist";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        if (getPrincipal().equals("anonymousUser")) {
            return "login";
        } else {
            persistentTokenBasedRememberMeServices.logout(request,
                    response,
                    SecurityContextHolder.getContext().getAuthentication());
            SecurityContextHolder.getContext().setAuthentication(null);
            return "logout";
        }
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String AccessDeniedPage(ModelMap modelMap) {
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/edit-user-{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, ModelMap modelMap) {
        User user = userService.findById(id);
        modelMap.addAttribute("editUser", user);
        return "editUser";
    }

    @RequestMapping(value = "/edit-user-{id}", method = RequestMethod.POST)
        public String updateUser(@Valid @ModelAttribute("editUser") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "editUser";
        }

        userService.updateUser(user);
        return "redirect:/admin-userslist";
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}


