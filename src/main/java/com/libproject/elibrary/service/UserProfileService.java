package com.libproject.elibrary.service;

import com.libproject.elibrary.model.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
