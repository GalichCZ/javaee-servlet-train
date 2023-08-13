package org.Utils;

import org.DTO.UserDto;
import org.Entity.User;

public class UserDtoBuilder {
    public static UserDto buildSpecificUserDto(String[] includedFields, User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());

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
                    userDto.setCity(user.getCity());
                    break;
                case "country":
                    userDto.setCountry(user.getCountry());
                    break;
            }
        }

        return userDto;
    }

    public static UserDto buildUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setCell(user.getCell());
        userDto.setCity(user.getCity());
        userDto.setCountry(user.getCountry());
        userDto.setGender(user.getGender());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());

        return userDto;
    }
}
