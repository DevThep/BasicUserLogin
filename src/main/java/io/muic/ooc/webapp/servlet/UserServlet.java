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
        name = "UserServlet",
        urlPatterns = {"/user"}
)
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("userList.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String user = req.getParameter("user");
        System.out.println("Deleting " + user);
        PrintWriter out = resp.getWriter();
        try{
            if(user!=null && mySQLJava.deleteUser(user)==0){
                out.print("good");
            }else{
                out.print("bad");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}