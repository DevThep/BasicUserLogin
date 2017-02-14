package io.muic.ooc.webapp.servlet;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static io.muic.ooc.webapp.WebApp.mySQLJava;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {
//    public static final String MYSQL_DRIVER = "com.webapp.cj.jdbc.Driver";
//    public static final String MYSQL_URL = "jdbc:webapp://localhost/ooc_test?"
//            + "user=ooc&password=oocpass";
//    MySQLJava mySQLJava = new MySQLJava(MYSQL_DRIVER,MYSQL_URL);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");
        System.out.println("{ User : " + username + "\n  Pass : " + password + "\n}");
        PrintWriter out = resp.getWriter();
        try {
            if (mySQLJava.validateLogin(username,password) == 0) {
//                System.out.println("SUCCESS!");
//                resp.setContentType("application/json");
//                String json_str = String.format("{\"name\":\"%s\"}",username);
//                JsonParser jsonParser = new JsonParser();
//                JsonElement element = jsonParser.parse(json_str);
                HttpSession session = req.getSession();
                session.setAttribute("user", username);
                out.print("login success!");
                out.flush();
            }
            else {
                out.print("bad");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}