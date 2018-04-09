package com.upgrad.ImageHoster.common;

import com.google.common.hash.Hashing;
import com.upgrad.ImageHoster.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


@SuppressWarnings("unchecked")
public class UserManager extends SessionManager {

    public User registerUser(final User user) {
        Session session = openSession();
        session.save(user);
        commitSession(session);
        return user;
    }



    public User getUserByName(final String username) {
        Session session = openSession();

        try {
            User user = (User)session.createCriteria(User.class)
                    .add(Restrictions.eq("username", username))
                    .uniqueResult();
            commitSession(session);

            return user;
        } catch(HibernateException e) {
            System.out.println("unable to retrieve an user from database by its username");
        }

        return null;
    }



    public User loginUser(final String username, final String password) {
        Session session = openSession();
        User user = getUserByName(username);
        commitSession(session);

        if (user == null)
            return null;

        String hashOfPassword = user.getPasswordHash();
        String hashOfEnteredPassword = Hashing.sha256()
                .hashString(password)
                .toString();

        if (hashOfPassword.equals(hashOfEnteredPassword)) {
            return user;
        } else {
            return null;
        }
    }
}

