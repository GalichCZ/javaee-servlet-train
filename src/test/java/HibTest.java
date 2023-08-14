import org.DBQuery.CityQuery;
import org.DBQuery.CountryQuery;
import org.Entity.City;
import org.Entity.Country;
import org.Entity.User;
import org.Provider.EntityManagerProvider;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.Utils.TransactionsHandle.persistTransaction;

public class HibTest {

    @Test
    public void write_user_to_table(){
        String countryName = "USA";

        Country country = CountryQuery.handleCountryQuery(countryName);

        String cityName = "Chicago";
        City city = CityQuery.handleCityQuery(cityName, country);

        User user = new User("MonaLisa12223", "Password", "Lisa", "emaidl@email.com", "female", "+420888999363", "cell", city);

        persistTransaction(user);

        EntityManagerProvider.closeConnection();
    }

    @Test
    public void get_user_from_table(){
        EntityManager em = EntityManagerProvider.getEntityManager();

        Long userId = 1L;

        TypedQuery<User> userQuery = em.createQuery(
                "SELECT u FROM User u " +
                        "JOIN FETCH u.city c " +
                        "JOIN FETCH c.country co " +
                        "WHERE u.id = :userId", User.class);
        userQuery.setParameter("userId", userId);

        User user = userQuery.getSingleResult();

        City city = user.getCity();
        Country country = city.getCountry();

        String userPoles = String.format("%s %s %s %s %s %s %s %s %s %s",
                user.getId(), user.getName(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.getGender(), user.getPhone(), user.getCell(),
                city.getCityName(), country.getCountryName()
                );

        System.out.println(userPoles);

        EntityManagerProvider.closeConnection();
    }
}
