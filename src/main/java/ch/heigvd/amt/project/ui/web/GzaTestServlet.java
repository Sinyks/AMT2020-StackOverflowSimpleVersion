package ch.heigvd.amt.project.ui.web;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/GzaPage", name = "GzaPage") // cant dynamically change this
public class GzaTestServlet extends HttpServlet {

    private String message = "Hello gza ! ";
    @Resource(lookup = "jdbc/gza")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Set response content type
        response.setContentType("text/html");

        try{
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stackoverflowsimple.users");
            ResultSet rs = ps.executeQuery();
            this.message = "satrt here ---> ";
            while (rs.next()){
                this.message += rs.getString("username") + " " + rs.getString("pk_user") +"\n";
            }

            ps.close();
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }
}
