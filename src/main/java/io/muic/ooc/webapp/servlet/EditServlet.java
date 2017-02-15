package io.muic.ooc.webapp.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static io.muic.ooc.webapp.WebApp.mySQLJava;

@WebServlet(
        name = "EditServlet",
        urlPatterns = {"/edit"}
)
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userEdit = req.getParameter("bt");
        String[] store;
        System.out.println(userEdit);
        store = mySQLJava.getEditInfo(userEdit);
        System.out.println(store[1]);
        System.out.println(store[2]);
        req.setAttribute("userEdit", userEdit);
        req.setAttribute("fname", store[1]);
        req.setAttribute("lname", store[2]);
        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
//        String userEdit = req.getParameter("bt");
//        String[] store;
//        System.out.println(userEdit);
//        store = mySQLJava.getEditInfo(userEdit);
//        System.out.println(store[1]);
//        System.out.println(store[2]);
//        req.setAttribute("userEdit", userEdit);
//        req.setAttribute("fname", store[1]);
//        req.setAttribute("lname", store[2]);
//        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
//        rd.forward(req, resp);

    }
}
