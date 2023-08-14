package org.DBQuery;
import org.Entity.City;
import org.Entity.Country;
import org.Provider.EntityManagerProvider;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import static org.Utils.TransactionsHandle.persistTransaction;

public class CityQuery {
    private static City getExactCity(String cityName) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        TypedQuery<City> cityQuery = em.createQuery(
                "SELECT c FROM City c WHERE c.cityName = :cityName", City.class);
        cityQuery.setParameter("cityName", cityName);

        try {
            return cityQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private static City createNewCity(String cityName, Country country) {
        City city = new City(cityName, country);

        persistTransaction(city);

        return city;
    }

    public static City handleCityQuery(String cityName, Country country) {
        City city = getExactCity(cityName);
        if(city != null){
            return city;
        }
        return createNewCity(cityName, country);
    }
}
