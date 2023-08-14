package org.Services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.DTO.UserDto;
import org.Entity.User;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import static org.DBQuery.UserQuery.getAllUsers;
import static org.DBQuery.UserQuery.getUserById;
import static org.Provider.JsonProvider.readTree;
import static org.Utils.RandomUsersUtils.readJsonTree;
import static org.Utils.TransactionsHandler.persistTransaction;
import static org.Utils.UserDtoBuilder.buildSpecificUserDto;
import static org.Utils.UserDtoBuilder.buildUserDto;


/*
    I could use here interface to describe the service class
    and use injections in all servlets where it needed to use
*/
public class UserServiceImpl {
    public static List<UserDto> writeAllUsersInDb(String randomUsers){
        try {
            JsonNode jsonTree = readTree(randomUsers);
            List<User> users = readJsonTree(jsonTree);
            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users){
                persistTransaction(user);
            }

            for (User user : users){
                userDtos.add(buildUserDto(user));
            }

            return userDtos;
        } catch (JsonProcessingException | NoSuchAlgorithmException | InvalidKeySpecException |
                 InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    public static List<UserDto> getExactAmountOfUsers(int offset, int limit){
        try {
            List<User> users = getAllUsers(offset, limit);

            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setEmail(user.getEmail());
                userDto.setUsername(user.getUsername());

                userDtos.add(userDto);
            }

            return userDtos;
        }
        catch (Exception ex){
            return null;
        }
    }

    public static UserDto getSpecificUser(String[] includedFields, long userId){
        try {
            User user = getUserById(userId);

            if (includedFields == null || includedFields[0].isEmpty()) {
                return buildUserDto(user);
            }

            return buildSpecificUserDto(includedFields, user);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
