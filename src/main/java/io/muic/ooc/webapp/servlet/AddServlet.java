package io.muic.ooc.webapp.servlet;



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
//        System.out.println("ADD { \n User : " + username + "\n  Pass : " + password + "\n}");
        PrintWriter out = resp.getWriter();
        try {
            if (username != null && password !=null && mySQLJava.addUser(username,password)==0) {
                out.print("add success");
                out.flush();
            }
            else {
                out.print("bad");
                out.flush();
                System.out.println("Username already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}