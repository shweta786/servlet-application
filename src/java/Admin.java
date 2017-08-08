/*
 *Admin class which is dedicated to the admin of apllication
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int exec = 0;
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Show Info</title>\n"
                +"<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">"
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
                + "\ntable {\n"
                + "  border-collapse: separate;\n"
                + "  border-spacing: 150px 0;"
                + "white-space: nowrap;"
                + "text-align:center\n"
                + "}\n"
                + "\n"
                + "th,td {\n"
                + " padding: 10px 0;\n"
                + "font-family:cursive;"
                + "font-size:170%;"
                + "}"
                + ".switch {\n"
                + "  position: relative;\n"
                + "  display: inline-block;\n"
                + "  width: 60px;\n"
                + "  height: 34px;\n"
                + "}\n"
                + "\n"
                + "/* Hide default HTML checkbox */\n"
                + ".switch input {display:none;}\n"
                + "\n"
                + "/* The slider */\n"
                + ".slider {\n"
                + "  position: absolute;\n"
                + "  cursor: pointer;\n"
                + "  top: 0;\n"
                + "  left: 0;\n"
                + "  right: 0;\n"
                + "  bottom: 0;\n"
                + "  background-color: #ccc;\n"
                + "  -webkit-transition: .4s;\n"
                + "  transition: .4s;\n"
                + "}\n"
                + "\n"
                + ".slider:before {\n"
                + "  position: absolute;\n"
                + "  content: \"\";\n"
                + "  height: 26px;\n"
                + "  width: 26px;\n"
                + "  left: 4px;\n"
                + "  bottom: 4px;\n"
                + "  background-color: #2196F3;\n"
                + "  -webkit-transition: .4s;\n"
                + "  transition: .4s;\n"
                + "}\n"
                + "\n"
                + "input:checked + .slider {\n"
                + "  background-color:white ;\n"
                + "}\n"
                + "\n"
                + "input:focus + .slider {\n"
                + "  box-shadow: 0 0 1px #2196F3;\n"
                + "}\n"
                + "\n"
                + "input:checked + .slider:before {\n"
                + "  -webkit-transform: translateX(26px);\n"
                + "  -ms-transform: translateX(26px);\n"
                + "  transform: translateX(26px);\n"
                + "}\n"
                + "\n"
                + "/* Rounded sliders */\n"
                + ".slider.round {\n"
                + "  border-radius: 34px;\n"
                + "}\n"
                + "\n"
                + ".slider.round:before {\n"
                + "  border-radius: 50%;\n"
                + "}"
                + ".pagination {\n"
                + "    display: inline-block;\n"
                + "}\n"
                + "\n"
                + ".pagination a {\n"
                + "    color: black;\n"
                + "    float: left;\n"
                + "    padding: 8px 16px;\n"
                + "    text-decoration: none;\n"
                + "}\n"
                + "\n"
                + ".pagination a.active {\n"
                + "    background-color: #4CAF50;\n"
                + "    color: white;\n"
                + "}\n"
                + "\n"
                + ".pagination a:hover:not(.active) {background-color: #ddd;}"
                + "</style></head><body><div class=\"wrapper\">\n"
                +"<center><image src=\"images/logo.png\"/></center>"
                + "<center><h1><u>User Details</u></h1>"
                + "<table><tr><th><font size=\"6\">Name</font></th><th><font size=\"6\">Email</font></th>"
                + "<th><font size=\"6\">More Info</font></th></tr><center></div>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/first", "root", "mindfire");
            ps = con.prepareStatement("select * from signup_info");
            rs = ps.executeQuery();
            if (!rs.next()) {
                out.println("No Registered User");
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    String name = ((((rs.getString("fname")).concat("  ")).concat(rs.getString("mname"))).concat("  "))
                            .concat(rs.getString("lname"));
                    String mail = rs.getString("email");
                    if (mail.equals("admin@admin.com")) {
                        continue;
                    }
                    exec++;
                    out.println("<tr><td>" + name + "</td><td class=\"mail\" id=" + Integer.toString(exec) + ">"
                            + mail + "</td> <td><a href=\"showinfo?mail=" + mail + "\">View Details</a></td></tr>\n");
                }
                out.println("</table>");
                out.println("</body></html>");
                con.close();
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
                con.close();
            } catch (Exception e) {
            }
        }

    }
}
