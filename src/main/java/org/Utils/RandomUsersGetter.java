package org.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomUsersGetter {
    /*
        Could use here something like jersey client, but this code
        is like show that I understand "pure" Java :D
    */
    public static String getRandomUsersFromApi() {
        String apiUrl = "https://randomuser.me/api/?results=10&inc=name,location,email,login,phone,cell,gender&nat=US";
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
}
