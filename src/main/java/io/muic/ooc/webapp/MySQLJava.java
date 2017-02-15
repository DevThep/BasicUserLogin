package io.muic.ooc.webapp;

import java.sql.*;
import java.util.ArrayList;

public class MySQLJava {

    enum TestTableColumns {
        id, TEXT;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String rt = "root";
    private String pw = "watermelon123";

    public MySQLJava(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public ArrayList<String> readData() throws Exception {
        ArrayList<String> lst = new ArrayList<>();
        try {
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while(resultSet.next()){
                String uname = resultSet.getString("username");
                String fname = resultSet.getString("firstname");
                String lname = resultSet.getString("lastname");
                String build = uname + " " + fname + " " + lname;
                lst.add(build);
            }
        } finally {
            close();
            return lst;
        }
    }
    public int validateLogin(String username,String password) throws  Exception {
        boolean success = false;
        try{
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                String existingUserName = resultSet.getString("username");
                String existingPassword = resultSet.getString("password");
                //System.out.println(existingUserName);//System.out.println(existingPassword);
                if (existingUserName.equals(username) && existingPassword.equals(password)) {
                    success = true;
                    break;
                }
            }
        }finally {
            close();
            if (success) return 0;
            else return 1;
        }
    }

    public int addUser(String username,String password) throws  Exception {
        boolean success = true;
        try{
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String existingUserName = resultSet.getString("username");
                //System.out.println(existingUserName);//System.out.println(existingPassword);
                if (existingUserName.equals(username)) {
                    success = false;
                    break;
                }
            }
        }finally {
            if (success){
                String queryString = "INSERT INTO users (username,password) VALUES (?,?)";
                PreparedStatement prepStmt = connection.prepareStatement(queryString);
                prepStmt.setString(1,username);
                prepStmt.setString(2,password);
                prepStmt.executeUpdate();
                close();
                return 0;
            }
            else {
                close();
                return -1;
            }
        }
    };

    public int editUser(String username,String firstname,String lastname) throws  Exception {
        boolean success = true;
        try{
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String existingUserName = resultSet.getString("username");
                if (existingUserName.equals(username)) {
                    success = false;
                    break;
                }
            }
        }finally {
            if (success){
                String queryString = "UPDATE users SET username=?,firstname=?,lastname=?";
                PreparedStatement prepStmt = connection.prepareStatement(queryString);
                prepStmt.setString(1,username);
                prepStmt.setString(2,firstname);
                prepStmt.setString(2,lastname);
                prepStmt.executeUpdate();
                close();
                return 0;
            }
            else {
                close();
                return -1;
            }
        }
    };

    public String[] getEditInfo(String username){
        String[] store = new String[3];
        try{
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            PreparedStatement prepStmt = connection.prepareStatement("SELECT username,firstname,lastname FROM users WHERE username=?");
            prepStmt.setString(1,username);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                String uname = resultSet.getString("username");
                String fname = resultSet.getString("firstname");
                String lname = resultSet.getString("lastname");
                store[0] = uname;
                if (fname == null){
                    store[1] = "";
                }else{
                    store[1] = fname;
                }
                if (lname == null){
                    store[2] = "";
                }else{
                    store[2] = lname;
                }
                break;
            }
            return store;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
            return store;
        }
    }

    public int deleteUser(String user) throws  Exception {
        boolean success = false;
        try{
            Class.forName(this.jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,rt,pw);
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM users WHERE username=?");
            prepStmt.setString(1,user);
            prepStmt.executeUpdate();
            success = true;
        }
        catch(Exception e){
            System.out.println("CANNOT DELETE");
        }
        finally {
            close();
            if (success) return 0;
            else return -1;
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
}
