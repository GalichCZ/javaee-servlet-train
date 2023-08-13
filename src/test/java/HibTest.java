import org.Entity.User;
import org.Provider.EntityManagerProvider;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibTest {

    @Test
    public void write_user_to_table(){
        EntityManager em = EntityManagerProvider.getEntityManager();

        User user = new User("Lisa12223", "Password", "Lisa", "emaidl@email.com", "female", "+420888999363", "cell", "Twin", "USA");

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        EntityManagerProvider.closeConnection();
    }
}
