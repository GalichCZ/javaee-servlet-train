package org.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public User(String username, String password, String name, String email,
                String gender, String phone, String cell, String city, String country) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.cell = cell;
        this.city = city;
        this.country = country;
    }

    public User() {

    }
}
