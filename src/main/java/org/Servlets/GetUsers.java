package org.Servlets;
import org.DTO.UserDto;
import org.Provider.JsonProvider;
import org.Services.UserServiceImpl;
import org.Utils.ErrorHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/users")
public class GetUsers extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        try {
            int offset = Integer.parseInt(request.getParameter("offset"));
            int limit = Integer.parseInt(request.getParameter("limit"));

            List<UserDto> userDtos = UserServiceImpl.getExactAmountOfUsers(offset, limit);

            if(userDtos == null){
                ErrorHandler.badRequest(response, "Cannot get users");
                return;
            }

            response.getWriter().write(JsonProvider.objectToString(userDtos));
        }
        catch (Exception e) {
            ErrorHandler.badRequest(response, e.getMessage());
        }
    }
}
