package io.muic.ooc.webapp.servlet;


import io.muic.ooc.webapp.InputCheck;

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
import static java.sql.JDBCType.NULL;

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
        if (store.length==1){
            resp.sendRedirect("/user");
        }else{
            req.setAttribute("userEdit", userEdit);
            if (store[1].equals("NULL")){
                store[1]="";
            }
            if (store[2].equals("NULL")){
                store[2]="";
            }
            req.setAttribute("fname", store[1]);
            req.setAttribute("lname", store[2]);
            RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
            rd.forward(req, resp);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String userEdit = req.getParameter("identity");
        String userNew = req.getParameter("name");
        String firstNew = req.getParameter("fname");
        String lastNew = req.getParameter("lname");
        InputCheck checker = new InputCheck();
        if (!checker.checkUser(userNew)){
            req.setAttribute("userEdit", userEdit);
            req.setAttribute("fname", firstNew);
            req.setAttribute("lname", lastNew);
            req.setAttribute("prob","Invalid username format!");
            RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
            rd.forward(req, resp);
            return;
        }
//        HttpSession session = req.getSession();
        if (userNew==null){
            userNew = userEdit;
        }
        System.out.println("Session " + req.getSession().getAttribute("user"));
        System.out.println("UserEdit : " + userEdit);
        if(req.getSession().getAttribute("user").equals(userEdit)){
            req.getSession().setAttribute("user",userNew);
            System.out.println("Session " + req.getSession().getAttribute("user"));
        }

        if(firstNew==null || firstNew==""){
            System.out.println("Here 1");
            firstNew= "NULL";
        }
        if(lastNew==null || lastNew==""){
            System.out.println("Here 2");
            lastNew="NULL";
        }
        if (mySQLJava.userExist(userEdit)){
            if(mySQLJava.update(userEdit,userNew,firstNew,lastNew)){
                System.out.println("here");
                resp.sendRedirect("/user");
            }else{
                resp.sendRedirect("/user");
            }
        }else{
            resp.sendRedirect("/user");
        }

    }
}
