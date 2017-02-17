package io.muic.ooc.webapp.servlet;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");
//        System.out.println("{ User : " + username + "\n  Pass : " + password + "\n}");
        PrintWriter out = resp.getWriter();
        try {
            if (username != null && password != null && mySQLJava.validateLogin(username,password) == 0) {
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