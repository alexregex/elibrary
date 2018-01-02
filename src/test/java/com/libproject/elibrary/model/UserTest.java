package com.libproject.elibrary.model;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class UserTest {

    private User user;
    private UserProfile userProfile;

    @Before
    public void init(){
        user = new User();
        userProfile = new UserProfile();
        userProfile.setType(UserProfileType.USER.toString());
        user.getUserProfiles().add(userProfile);

    }

    @Test
    public void testRoleUser() {
        assertThat(user.getUserProfiles()
                .stream()
                .anyMatch(userProfile -> userProfile.getType().equals("USER")), is(equalTo(true)));

        assertThat(user.getUserProfiles()
                .stream()
                .anyMatch(userProfile -> userProfile.getType().equals("ADMIN")), is(equalTo(false)));

        userProfile.setType(UserProfileType.ADMIN.toString());
        user.getUserProfiles().add(userProfile);

        assertThat(user.getUserProfiles()
                .stream()
                .anyMatch(userProfile -> userProfile.getType().equals("ADMIN")), is(equalTo(true)));
    }
}