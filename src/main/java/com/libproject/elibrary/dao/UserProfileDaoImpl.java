package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer,UserProfile> implements UserProfileDao {
    @Override
    public UserProfile findById(int id) {
        return getByKey(id);
    }

    @Override
    public UserProfile findByType(String type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        return (UserProfile) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserProfile> findAllUserProfiles() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("type"));
        return (List<UserProfile>) criteria.list();
    }
}
