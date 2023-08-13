package org.Servlets;
import org.DTO.UserDto;
import org.Provider.JsonProvider;
import org.Services.UserServiceImpl;
import org.Utils.ErrorHandler;
import org.Utils.RandomUsersGetter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/user-generator")
public class UserGenerator extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        try {
            String randomUsersResponse = RandomUsersGetter.getRandomUsersFromApi();

            if(randomUsersResponse.isEmpty()){
                ErrorHandler.badRequest(response, "cannot get random users");
                return;
            }

            List<UserDto> userDtos = UserServiceImpl.writeAllUsersInDb(randomUsersResponse);

            response.getWriter().write(JsonProvider.objectToString(userDtos));
        }
        catch (Exception e){
            ErrorHandler.badRequest(response, e.getMessage());
        }
    }

    public void destroy() {
    }
}
