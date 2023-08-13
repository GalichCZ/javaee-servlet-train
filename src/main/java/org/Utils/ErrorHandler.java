package org.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler {
    public static void badRequest(HttpServletResponse response, String error) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Bad request: " + error);
    }
}