package org.Utils;

import org.Provider.EntityManagerProvider;

import javax.persistence.EntityManager;

public class TransactionsHandler {
    public static void persistTransaction(Object object){
        EntityManager em = EntityManagerProvider.getEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

}
