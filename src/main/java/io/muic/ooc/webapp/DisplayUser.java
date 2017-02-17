package io.muic.ooc.webapp;

import java.sql.ResultSet;
import java.util.ArrayList;

import static io.muic.ooc.webapp.WebApp.mySQLJava;


public class DisplayUser {

    public String view(String user) throws Exception{
        ArrayList<String> rs =  mySQLJava.readData();
        StringBuilder sb=new StringBuilder();
        StringBuilder userInfo=new StringBuilder();
        System.out.println("Current Session : " + user);
        for(String i: rs){
            String[] split = i.split(" ");
            String u = split[0];
            String fn = split[1];
            String ln = split[2];
            if (fn.equals("null") || fn.equals("NULL")){
                fn = "";
            }
            if(ln.equals("null") || ln.equals("NULL")){
                ln = "";
            }
            if (!u.equals(user)){
                String entry = String.format("<tr><td>%s</td><td>%s</td><td>%s</td>",u,fn,ln);
                sb.append(entry);
                sb.append(String.format("<td><form action=\"edit\" method=\"get\">" + "<input type=\"hidden\" name=\"bt\" value=%s>" +
                        "<input type=\"submit\" value=\"Edit\" class=\"editBtn btn btn-primary pull-right\"></form>",u));
                sb.append(String.format("<a href=\"#\" class=\"btn btn-primary pull-right\" data-record-title=\"%s\" " +
                        "data-toggle=\"modal\" data-target=\"#confirm-delete\">Delete</a></td></tr>",u));
            }else{
                String userEntry = String.format("<tr><td><b>%s</b></td><td>%s</td><td>%s</td>",u,fn,ln);
                userInfo.append(userEntry);
                userInfo.append(String.format("<td><form action=\"edit\" method=\"get\">" + "<input type=\"hidden\" name=\"bt\" value=%s>" +
                        "<input type=\"submit\" value=\"Edit\" class=\"editBtn btn btn-primary pull-right\"></form>",u));
            }
        }
        return userInfo.toString() + sb.toString();
    }
}
