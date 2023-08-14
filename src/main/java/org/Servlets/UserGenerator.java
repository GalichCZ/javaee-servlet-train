package org.Servlets;
import org.DTO.UserDto;
import org.Provider.JsonProvider;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static org.Services.UserServiceImpl.writeAllUsersInDb;
import static org.Utils.ErrorHandler.badRequest;
import static org.Utils.RandomUsersUtils.getRandomUsersFromApi;

@WebServlet(value = "/user-generator")
public class UserGenerator extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        try {
            String randomUsersResponse = getRandomUsersFromApi();

            if(randomUsersResponse.isEmpty()){
                badRequest(response, "cannot get random users");
                return;
            }

            List<UserDto> userDtos = writeAllUsersInDb(randomUsersResponse);

            response.getWriter().write(JsonProvider.objectToString(userDtos));
        }
        catch (Exception e){
            badRequest(response, e.getMessage());
        }
    }
}
