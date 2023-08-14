package org.Entity;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country (){}

    public String getCountryName() {
        return countryName;
    }
}
