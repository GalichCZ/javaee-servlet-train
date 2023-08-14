package org.Utils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.DTO.UserDto;
import org.Entity.City;
import org.Entity.Country;
import org.Entity.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserDtoBuilder {
    public static UserDto buildSpecificUserDto(String[] includedFields, User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        City city = user.getCity();
        Country country = city.getCountry();

        /*
            These strings in case should be changed for reflection of class pole names in case of
            continuously supporting this app. Possible runtime error - changed pole name like password to pswd
        */
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

    public static UserDto buildUserDto(User user) throws InvocationTargetException, IllegalAccessException {
        UserDto userDto = new UserDto();

        City city = user.getCity();
        Country country = city.getCountry();

        BeanUtils.copyProperties(userDto, user);

        userDto.setCity(city.getCityName());
        userDto.setCountry(country.getCountryName());

        return userDto;
    }
}
