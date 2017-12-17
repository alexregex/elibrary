package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<Integer,User> implements UserDao {

    @Override
    public User findById(int id) {
        User user =  getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return  user;
    }

    @Override
    public User findByLogin(String login) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public void removeUser(User user) {
        delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
}
