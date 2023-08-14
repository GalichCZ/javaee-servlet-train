import org.Entity.User;
import org.Provider.EntityManagerProvider;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import java.util.List;

public class HibTest {

    @Test
    public void get_users_from_table(){
        EntityManager em = EntityManagerProvider.getEntityManager();

        em.getTransaction().begin();

        em.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult(0).setMaxResults(10).getResultList();

        em.getTransaction().commit();

        EntityManagerProvider.closeConnection();
    }
}
