package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAllUserProfiles();
}
