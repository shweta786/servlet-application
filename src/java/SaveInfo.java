/*
 * SaveInfo class which saves the information edited by user
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        int sports = 0, books = 0, sw = 0, photo = 0, fashion = 0, sing = 0;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie ck[] = request.getCookies();
        String mailPhone = ck[0].getValue();
        try {
            String address = request.getParameter("add");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            String zip_code = request.getParameter("zip");
            if ("".equals(zip_code)) {
                zip_code = "0";
            }
            String address1 = request.getParameter("add1");
            String city1 = request.getParameter("city1");
            String state1 = request.getParameter("state1");
            String country1 = request.getParameter("country1");
            String zip_code1 = request.getParameter("zip1");
            if(country1.equals("-select one-")) {
                country1="";
            }
            if(country.equals("-select one-")) {
                country="";
            }
            if ("".equals(zip_code1)) {
                zip_code1 = "0";
            }
            if(request.getParameter( "sports" ) != null){
                sports=1;
            }
            if(request.getParameter( "books" ) != null){
                books=1;
            }
            if(request.getParameter( "sw" ) != null){
                sw=1;
            }
            if(request.getParameter( "photo" ) != null){
                photo=1;
            }
            if(request.getParameter( "fashion" ) != null){
                fashion=1;
            }
            if(request.getParameter( "sing" ) != null){
                sing=1;
            }
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            ps = con.prepareStatement("UPDATE signup_info SET address1=?,"
                    + " city1=?, state1=?, country1=?, zipcode1=?, address2=?, city2=?,"
                    + " state2=?, country2=?, zipcode2=?, sport=?, books=?, sw=?, photo=?, fashion=?, sing=?"
                    + " WHERE email=?");
            ps.setString(1, address);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, country);
            ps.setInt(5, Integer.parseInt(zip_code));
            ps.setString(6, address1);
            ps.setString(7, city1);
            ps.setString(8, state1);
            ps.setString(9, country1);
            ps.setInt(10, Integer.parseInt(zip_code1));
            ps.setInt(11,sports);
            ps.setInt(12, books);
            ps.setInt(13, sw);
            ps.setInt(14, photo);
            ps.setInt(15, fashion);
            ps.setInt(16, sing);
            ps.setString(17, mailPhone);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("Updated Successfully");
            }
        } catch (NumberFormatException e) {
            out.println("Please enter Valid zip code");
            RequestDispatcher rd = request.getRequestDispatcher("/infohtml.html");
            rd.include(request, response);
        } catch (Exception e) {
            out.println(e);
        }
         finally {
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
