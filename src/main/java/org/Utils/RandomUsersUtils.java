package org.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.Entity.City;
import org.Entity.Country;
import org.Entity.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import static org.DBQuery.CityQuery.handleCityQuery;
import static org.DBQuery.CountryQuery.handleCountryQuery;
import static org.Utils.HashPassword.hashPasswordWithPBKDF;

public class RandomUsersUtils {
    /*
        Could use here something like jersey client, but this code
        is like show that I understand "pure" Java :D
    */
    public static String getRandomUsersFromApi() {
        String apiUrl = "https://randomuser.me/api/?results=10&inc=name,location,email,login,phone,cell,gender";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder clientResponse = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                clientResponse.append(inputLine);
            }
            in.close();

            return clientResponse.toString();
        } catch (Exception ex){
            return "";
        }
    }

    public static List<User> readJsonTree(JsonNode jsonTree) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeySpecException {
        List<User> users = new ArrayList<>();
        JsonNode results = jsonTree.get("results");

        for (JsonNode result : results){
            JsonNode fullName = result.get("name");
            JsonNode location = result.get("location");
            JsonNode login = result.get("login");
            String email = result.get("email").asText();
            String phone = result.get("phone").asText();
            String cell = result.get("cell").asText();
            String username = login.get("username").asText();
            //in real case password should be encrypted, so I made it here also :)
            String password = hashPasswordWithPBKDF(login.get("password").asText());
            String countryName = location.get("country").asText();
            Country country = handleCountryQuery(countryName);
            String cityName = location.get("city").asText();
            City city = handleCityQuery(cityName, country);
            String gender = result.get("gender").asText();
            //also there could be used string builder but in this case it won't "save" any time or code length
            String name = String.format("%s %s %s", fullName.get("title").asText(),
                    fullName.get("first").asText(), fullName.get("last").asText());

            users.add(new User(username, password, name, email, gender, phone, cell, city));
        }

        return users;
    }

}
