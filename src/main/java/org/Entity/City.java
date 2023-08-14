package org.Entity;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }

    public City(){}

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }
}
