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

@WebServlet(value = "/user")
public class GetSpecificUser extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        try {
            String inc = request.getParameter("inc");

            String [] includedFields = inc == null ? null : inc.split(",");

            long userId = Long.parseLong(request.getParameter("id"));

            UserDto userDto = UserServiceImpl.getSpecificUser(includedFields, userId);

            if(userDto == null){
                ErrorHandler.badRequest(response, "Cannot get specific user");
                return;
            }

            response.getWriter().write(JsonProvider.objectToString(userDto));
        }catch (Exception e){
            ErrorHandler.badRequest(response, e.getMessage());
        }
    }
}
