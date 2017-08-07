/*
 * ShowInfo class for showing details to corresponding user 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = "", contact = "", dob = "", c_add = "", p_add = "", interest = "";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String mailPhone = request.getParameter("mail");
        out.println("<html><head><title>Show Info</title>\n"
                + "<style> body {\n"
                + "  height:100%;\n"
                + "}\n"
                + ".wrapper {\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "body {    \n"
                + "  /* Change this color to change the overall feeling :D */\n"
                + "  background-color: #999;\n"
                + "  \n"
                + "  background-image: \n"
                + "    linear-gradient(180deg, rgba(0,0,0,.1) 0%, rgba(0,0,0,.2) 100%),\n"
                + "    linear-gradient(90deg, transparent 50%, rgba(255,255,255,.4) 50%),\n"
                + "    linear-gradient(180deg, transparent 50%, rgba(255,255,255,.2) 50%),\n"
                + "    linear-gradient(90deg, transparent 50%, rgba(255,255,255,.2) 50%),\n"
                + "    linear-gradient(-180deg, transparent 50%, rgba(255,255,255,.4) 50%),\n"
                + "    linear-gradient(-90deg, transparent 50%, rgba(255,255,255,.4) 50%);\n"
                + "  background-size:\n"
                + "    4em 4em, \n"
                + "    4em 4em, \n"
                + "    2em 2em, \n"
                + "    2em 2em, \n"
                + "    1em 1em, \n"
                + "    1em 1em;\n"
                + "}\n"
                + "\n"
                + "</style></head><body><div class=\"wrapper\">\n"
                + "<center><image src=\"images/logo.png\"/></center><br>"
                + "<form action=\"infohtml.html\" method=\"post\">"
                + "<button id=\"confirm\" type=\"submit\" name=\"confirm\" style=\"width:150px; "
                + "height: 40px; font-family:cursive; font-size:130%\"><b>Edit Info</b></button></form>\n"
                + "</div></body></html>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            ps = con.prepareStatement("select * from signup_info where email=? or phone=?");
            ps.setString(1, mailPhone);
            ps.setString(2, mailPhone);
            rs = ps.executeQuery();
            rs.next();
            name = ((((rs.getString("fname")).concat("  ")).concat(rs.getString("mname"))).concat("  "))
                    .concat(rs.getString("lname"));
            contact = rs.getString("phone");
            dob = rs.getString("dob");
            c_add = ((((((rs.getString("address1")).concat("  ")).concat(rs.getString("city1")))
                    .concat("  ")).concat(rs.getString("state1"))).concat(rs.getString("country1")))
                    .concat(rs.getString("zipcode1"));
            p_add = ((((((rs.getString("address2")).concat("  ")).concat(rs.getString("city2")))
                    .concat("  ")).concat(rs.getString("state2"))).concat(rs.getString("country2")))
                    .concat(rs.getString("zipcode2"));
            if (rs.getInt("sport") == 1) {
                interest = "Sports,";
            }
            if (rs.getInt("books") == 1) {
                interest = interest.concat(" Books,");
            }
            if (rs.getInt("sw") == 1) {
                interest = interest.concat(" Computer/Software,");
            }
            if (rs.getInt("photo") == 1) {
                interest = interest.concat(" Photography,");
            }
            if (rs.getInt("fashion") == 1) {
                interest = interest.concat(" fashion,");
            }
            if (rs.getInt("sing") == 1) {
                interest = interest.concat(" Singing/Dancing");
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
        out.println("<center>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Name:-     " + name + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Email-id:-     " + mailPhone + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Contact Number:-     " + contact + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">D.O.B:-     " + dob + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Current Address:-    " + c_add + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Permanent Address:-     " + p_add + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Personal Interests:-     " + interest + "</p>\n"
                + "<form action=\"logout\" method=\"post\">"
                + "<button id=\"confirm\" type=\"submit\" name=\"confirm\" style=\"width:150px; "
                + "height: 40px; font-family:cursive; font-size:130%\"><b>LogOut</b></button></form>"
                + "</center>"
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = "", contact = "", dob = "", c_add = "", p_add = "", interest = "";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String mailPhone = request.getParameter("mail");
        out.println("<html><head><title>Show Info</title>\n"
                + "<style> body {\n"
                + "  height:100%;\n"
                + "}\n"
                + ".wrapper {\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "body {    \n"
                + "  /* Change this color to change the overall feeling :D */\n"
                + "  background-color: #999;\n"
                + "  \n"
                + "  background-image: \n"
                + "    linear-gradient(180deg, rgba(0,0,0,.1) 0%, rgba(0,0,0,.2) 100%),\n"
                + "    linear-gradient(90deg, transparent 50%, rgba(255,255,255,.4) 50%),\n"
                + "    linear-gradient(180deg, transparent 50%, rgba(255,255,255,.2) 50%),\n"
                + "    linear-gradient(90deg, transparent 50%, rgba(255,255,255,.2) 50%),\n"
                + "    linear-gradient(-180deg, transparent 50%, rgba(255,255,255,.4) 50%),\n"
                + "    linear-gradient(-90deg, transparent 50%, rgba(255,255,255,.4) 50%);\n"
                + "  background-size:\n"
                + "    4em 4em, \n"
                + "    4em 4em, \n"
                + "    2em 2em, \n"
                + "    2em 2em, \n"
                + "    1em 1em, \n"
                + "    1em 1em;\n"
                + "}\n"
                + "\n"
                + "</style></head><body><div class=\"wrapper\">");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            ps = con.prepareStatement("select * from signup_info where email=? or phone=?");
            ps.setString(1, mailPhone);
            ps.setString(2, mailPhone);
            rs = ps.executeQuery();
            rs.next();
            name = ((((rs.getString("fname")).concat("  ")).concat(rs.getString("mname"))).concat("  "))
                    .concat(rs.getString("lname"));
            contact = rs.getString("phone");
            dob = rs.getString("dob");
            c_add = ((((((rs.getString("address1")).concat("  ")).concat(rs.getString("city1")))
                    .concat("  ")).concat(rs.getString("state1"))).concat(rs.getString("country1")))
                    .concat(rs.getString("zipcode1"));
            p_add = ((((((rs.getString("address2")).concat("  ")).concat(rs.getString("city2")))
                    .concat("  ")).concat(rs.getString("state2"))).concat(rs.getString("country2")))
                    .concat(rs.getString("zipcode2"));
            if (rs.getInt("sport") == 1) {
                interest = "Sports,";
            }
            if (rs.getInt("books") == 1) {
                interest = interest.concat(" Books,");
            }
            if (rs.getInt("sw") == 1) {
                interest = interest.concat(" Computer/Software,");
            }
            if (rs.getInt("photo") == 1) {
                interest = interest.concat(" Photography,");
            }
            if (rs.getInt("fashion") == 1) {
                interest = interest.concat(" fashion,");
            }
            if (rs.getInt("sing") == 1) {
                interest = interest.concat(" Singing/Dancing");
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
        out.println("<center>\n"
                + "<center><image src=\"images/logo.png\"/></center>"
                + "<p style=\"font-family:cursive; font-size:200%\">Name:-     " + name + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Email-id:-     " + mailPhone + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Contact Number:-     " + contact + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">D.O.B:-     " + dob + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Current Address:-    " + c_add + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Permanent Address:-     " + p_add + "</p>\n"
                + "<p style=\"font-family:cursive; font-size:200%\">Personal Interests:-     " + interest + "</p>\n"
                + "</center></div><div class=\"wrapper\">\n"
                + "<button id=\"activate\" type=\"submit\" name=\"activate\" style=\"width:150px; "
                + "height: 40px; font-family:cursive; font-size:130%\">"
                + "<b><a href=\"changestatus?mail=" + mailPhone + "&status=activate\">Activate</a></b></button>"
                + "<button id=\"deactivate\" type=\"submit\" name=\"deactivate\" style=\"width:150px; "
                + "height: 40px; font-family:cursive; font-size:130%\">"
                + "<b><a href=\"changestatus?mail=" + mailPhone + "&status=deactivate\">Deactivate</a></b></button>\n"
                + "</div><br><br>"
                + "<center>"
                + "<form action=\"logout\" method=\"post\">"
                + "<button id=\"confirm\" type=\"submit\" name=\"confirm\" style=\"width:150px; "
                + "height: 40px; font-family:cursive; font-size:130%\"><b>LogOut</b></button></form></center>"
                + "</body></html>");

    }

}
