package org.DBQuery;
import org.Entity.Country;
import org.Provider.EntityManagerProvider;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import static org.Utils.TransactionsHandle.persistTransaction;

public class CountryQuery {

    private static Country getExactCountry(String countryName) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        TypedQuery<Country> cityQuery = em.createQuery(
                "SELECT c FROM Country c WHERE c.countryName = :countryName", Country.class);
        cityQuery.setParameter("countryName", countryName);

        try {
            return cityQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private static Country createNewCountry(String countryName) {
        Country country = new Country(countryName);

        persistTransaction(country);

        return country;
    }

    public static Country handleCountryQuery(String countryName) {
        Country city = getExactCountry(countryName);
        if(city != null){
            return city;
        }
        return createNewCountry(countryName);
    }

}
