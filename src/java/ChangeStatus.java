/*
 Class which is made for activating/Deactivating user by admin
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement psa = null;
        ResultSet rs = null;
        response.setContentType("text/html");
        String mail = request.getParameter("mail");
        PrintWriter out = response.getWriter();
        try {
            String status = request.getParameter("status");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            psa = con.prepareStatement("Select * from first.signup_info WHERE email=?");
            psa.setString(1, mail);
            rs = psa.executeQuery();
            rs.next();
            if (status.equals("activate")) {
                if (rs.getInt("activate") == 1) {
                    out.println("Already activated");
                } else {
                    ps = con.prepareStatement("UPDATE first.signup_info SET activate=1 WHERE email=?");
                    ps.setString(1, mail);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        out.println("Successfully activated");
                    } else {
                        out.println("Error occured. Try again");
                    }
                }
            } else {
                if (rs.getInt("activate") == 0) {
                    out.println("Already deactivated");
                } else {
                    ps = con.prepareStatement("UPDATE first.signup_info SET activate=0 WHERE email=?");
                    ps.setString(1, mail);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        out.println("Successfully deactivated");
                    } else {
                        out.println("Error occured. Try again");
                    }
                }
            }
        } catch (Exception e) {
            out.println(e);
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
                psa.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

}
