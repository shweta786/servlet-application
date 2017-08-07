/*
 * Sign-Up file for registering users to save and share their details
*/
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pst = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String f_name = request.getParameter("name");
            String m_name = request.getParameter("mname");
            String l_name = request.getParameter("lname");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String password = MD5.getHash(request.getParameter("pass"));            
            int exec;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");           
            pst = con.prepareStatement(
                    "insert into signup_info(fname,mname,lname,email,dob,gender,phone,password) "
                            + "values(?,?,?,?,?,?,?,?)");
            pst.setString(1, f_name);
            pst.setString(2, m_name);
            pst.setString(3, l_name);
            pst.setString(4, email);
            pst.setString(5, dob);
            pst.setString(6, gender);
            pst.setString(7, phone);
            pst.setString(8, password);
            exec = pst.executeUpdate();
            if (exec != 0) {
                out.println("<br><b>Successfully Registered.   Login to see and edit your details<b>");
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.include(request, response);
            } else {
                out.println("Sorry, Try Again");
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.include(request, response);
            }
        } catch(MySQLIntegrityConstraintViolationException e) {
            out.println("This email-id is already registered with us, Please try with some other");
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            rd.include(request, response);
        }        
        catch (Exception e) {
            out.println("Something went wrong");
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                }
            try {
                con.close();
            } catch (Exception e) {
                }
        }

    }
}
