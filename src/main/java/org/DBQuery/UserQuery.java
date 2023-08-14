package org.DBQuery;

import org.Entity.User;
import org.Provider.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;

public class UserQuery {
    private static final EntityManager em = EntityManagerProvider.getEntityManager();

    public static List<User> getAllUsers(int offset, int limit){
        em.getTransaction().begin();

        List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();

        em.getTransaction().commit();

        return users;
    }

    public static User getUserById(long userId){
        em.getTransaction().begin();

        User user = em.find(User.class, userId);

        em.getTransaction().commit();

        return user;
    }
}
