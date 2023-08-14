package org.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    private String gender;
    private String phone;
    private String cell;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


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

    public City getCity() {
        return city;
    }


    public User(String username, String password, String name, String email,
                String gender, String phone, String cell, City city) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.cell = cell;
        this.city = city;
    }

    public User() {

    }
}
