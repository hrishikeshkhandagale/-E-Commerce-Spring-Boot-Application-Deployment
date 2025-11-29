package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;

@Repository
@Transactional
public class userDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUser() {
        return entityManager.createQuery("from CUSTOMER", User.class).getResultList();
    }

    public User saveUser(User user) {
        entityManager.merge(user);
        System.out.println("User added: " + user.getId());
        return user;
    }

    public User getUser(String username, String password) {
        try {
            Query query = entityManager.createQuery("from CUSTOMER where username = :username");
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();

            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return new User();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    public boolean userExists(String username) {
        Query query = entityManager.createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }

    public User getUserByUsername(String username) {
        try {
            Query query = entityManager.createQuery("from CUSTOMER where username = :username");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
