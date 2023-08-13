package org.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.DTO.UserDto;
import org.Entity.User;
import org.Provider.EntityManagerProvider;
import org.Provider.JsonProvider;
import org.Utils.TreeReaderUtil;
import org.Utils.UserDtoBuilder;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


/*
    I could use here interface to describe the service class
    and use injections in all servlets where it needed to use
*/
public class UserServiceImpl {
    private static final EntityManager em = EntityManagerProvider.getEntityManager();

    public static List<UserDto> writeAllUsersInDb(String randomUsers){
        try {
            JsonNode jsonTree = JsonProvider.readTree(randomUsers);
            List<User> users = TreeReaderUtil.readJsonTree(jsonTree);
            List<UserDto> userDtos = new ArrayList<>();

            em.getTransaction().begin();
            for (User user : users){
                em.persist(user);
            }

            em.getTransaction().commit();

            for (User user : users){
                userDtos.add(UserDtoBuilder.buildUserDto(user));
            }

            return userDtos;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static List<UserDto> getExactAmountOfUsers(int offset, int limit){
        try {
            em.getTransaction().begin();

            List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                    .setFirstResult(offset).setMaxResults(limit).getResultList();

            em.getTransaction().commit();

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
            em.getTransaction().begin();

            User user = em.find(User.class, userId);

            em.getTransaction().commit();

            if (includedFields == null || includedFields[0].isEmpty()) {
                return UserDtoBuilder.buildUserDto(user);
            }

            return UserDtoBuilder.buildSpecificUserDto(includedFields, user);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
