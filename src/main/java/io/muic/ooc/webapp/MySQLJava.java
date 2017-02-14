package io.muic.ooc.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJava {

    enum TestTableColumns {
        id, TEXT;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Connection connection2;
    private Statement statement;
    private Statement statement2;
    private ResultSet resultSet;
    private ResultSet resultSet2;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;

    public MySQLJava(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;

    }

    public void readData() throws Exception {
        try {
            //Class.forName(this.jdbcDriverStr);
            connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","watermelon123");

            //statement = connection.createStatement();
            statement2 = connection2.createStatement();
            resultSet2 = statement2.executeQuery("select * from users");
            //resultSet = statement.executeQuery("select * from HelloWorld;");
            //preparedStatement2 = connection2.prepareStatement("insert into test.users values ('admin','password')");
            //preparedStatement = connection.prepareStatement("insert into ok.HelloWorld values (13,19)");
            //preparedStatement2.executeUpdate();
            //getResultSet(resultSet2);
//            while (resultSet2.next()) {
//                //Integer email = resultSet2.getInt("Email");
//                String email2 = resultSet2.getString("username");
//                System.out.println(email2);
//            }
            
            //System.out.println("Done");
        } finally {
            close();
        }
    }
    public int validateLogin(String username,String password) throws  Exception {
        readData();
        while (resultSet2.next()) {

            String existingUserName = resultSet2.getString("username");
            String existingPassword = resultSet2.getString("password");
            //System.out.println(existingUserName);
            //System.out.println(existingPassword);
            if (existingUserName.equals(username) && existingPassword.equals(password)) {
                return 0;

            }

        }
        return -1;

    };


    private void getResultSet(ResultSet resultSet) throws Exception {
        while (resultSet.next()) {
            Integer email= resultSet.getInt("idHelloWorld");
            Integer firstName = resultSet.getInt("supah");
            System.out.print("email: " + email);
            System.out.print(", firstname: " + firstName);
            System.out.println();
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
