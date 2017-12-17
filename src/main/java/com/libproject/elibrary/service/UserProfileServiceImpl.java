package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.UserProfileDao;
import com.libproject.elibrary.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userProfileServiceImpl")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile findById(int id) {
        return userProfileDao.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAllUserProfiles();
    }
}
