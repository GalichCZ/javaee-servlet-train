package org.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static EntityManagerFactory getEntityMangerFactory(){
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    public static EntityManager getEntityManager(){
        if(em == null){
            em = getEntityMangerFactory().createEntityManager();
        }
        return em;
    }

    public static void closeConnection(){
        boolean isEmfOpen = emf != null && emf.isOpen();
        boolean isEmOpen = em != null && em.isOpen();

        if(isEmfOpen && isEmOpen){
            emf.close();
            em.close();
        }
    }
}
