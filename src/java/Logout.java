/*
 * Class to logout user
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
        Cookie ck = new Cookie("mailPhone", "");    //deleting vlaue of cookie
        ck.setMaxAge(0);
        response.addCookie(ck);
        response.getWriter().println("Successfully Logout");
    }

}
