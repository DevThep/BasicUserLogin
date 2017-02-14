package io.muic.ooc.webapp.servlet;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static io.muic.ooc.webapp.WebApp.mySQLJava;

@WebServlet(
        name = "AddServlet",
        urlPatterns = {"/add"}
)
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String username = req.getParameter("user");
        String password = req.getParameter("pass");
        System.out.println("{ User : " + username + "\n  Pass : " + password + "\n}");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
            if (mySQLJava.validateLogin(username,password) == 0) {
                String json_str = String.format("{\"name\":\"%s\"}",username);
                JsonParser jsonParser = new JsonParser();
                JsonElement element = jsonParser.parse(json_str);;
                out.print(element);
                out.flush();
            }
            else {
                System.out.println("Username already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}