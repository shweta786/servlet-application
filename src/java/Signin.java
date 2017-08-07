/*
 * Signin class which checks the email/phone and coreesponding password of user
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String mailPhone = request.getParameter("mail");
            String password = MD5.getHash(request.getParameter("password"));
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            ps = con.prepareStatement("select * from signup_info where (email=? or phone=?) and password=?");
            ps.setString(1, mailPhone);
            ps.setString(2, mailPhone);
            ps.setString(3, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                if ((request.getParameter("mail")).equals("admin@admin.com")) {
                    out.println("Incorrect Password");
                } else {
                    out.println("Wrong Credentials ");
                }
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.include(request, response);
            } else {
                if (rs.getInt("activate") == 0) {
                    out.print(" Your Account Has Been Deactivated ");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                    rd.include(request, response);
                } else {
                    Cookie ck = new Cookie("mailPhone", mailPhone);
                    response.addCookie(ck);
                    if ((request.getParameter("mail")).equals("admin@admin.com")) {
                        RequestDispatcher rd = request.getRequestDispatcher("admin");
                        rd.forward(request, response);
                    } else {
                        out.println(password);
                        RequestDispatcher rd = request.getRequestDispatcher("showinfo");
                        rd.forward(request, response);
                    }
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }
}
