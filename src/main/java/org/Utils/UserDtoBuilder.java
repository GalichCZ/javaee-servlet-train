package org.Utils;
import org.DTO.UserDto;
import org.Entity.City;
import org.Entity.Country;
import org.Entity.User;

public class UserDtoBuilder {
    public static UserDto buildSpecificUserDto(String[] includedFields, User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        City city = user.getCity();
        Country country = city.getCountry();

        for(String field : includedFields){
            switch (field){
                case "username":
                    userDto.setUsername(user.getUsername());
                    break;
                case "password":
                    userDto.setPassword(user.getPassword());
                    break;
                case "name":
                    userDto.setName(user.getName());
                    break;
                case "email":
                    userDto.setEmail(user.getEmail());
                    break;
                case "gender":
                    userDto.setGender(user.getGender());
                    break;
                case "phone":
                    userDto.setPhone(user.getPhone());
                    break;
                case "cell":
                    userDto.setCell(user.getCell());
                    break;
                case "city":
                    userDto.setCity(city.getCityName());
                    break;
                case "country":
                    userDto.setCountry(country.getCountryName());
                    break;
            }
        }

        return userDto;
    }

    public static UserDto buildUserDto(User user){
        UserDto userDto = new UserDto();

        City city = user.getCity();
        Country country = city.getCountry();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setCell(user.getCell());
        userDto.setCity(city.getCityName());
        userDto.setCountry(country.getCountryName());
        userDto.setGender(user.getGender());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());

        return userDto;
    }
}
