<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Users</title>

    <!-- bootstrap -->
    <link rel="icon" href="images/transformer.ico">
    <link href="css/userList/bootstrap.css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/layout.css">
    <link rel="stylesheet" type="text/css" href="css/compiled/elements.css">
</head>

<body>
    <%
        response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
        response.addHeader("Pragma", "no-cache");
        response.addDateHeader ("Expires", 0);
        if(session.getAttribute("user")==null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <div class="col-md-12" id="content-wrapper">
        <div class="row">
            <div class="col-lg-12">

                <div class="clearfix">
                    <h1>
                        <%=session.getAttribute("user")%>
                    </h1>
                    <h2 class="pull-left">Users</h2>

                    <div class="pull-right top-page-ui">
                        <a href="/add" class="btn btn-primary pull-right">
                            <i class="fa fa-plus-circle fa-lg"></i> Add user
                        </a>
                    </div>
                    <div class="pull-right top-page-ui">
                        <a href="logout.jsp" class="btn btn-primary pull-right">
                            <i class="fa fa-sign-out fa-lg"></i> Sign-out
                        </a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box clearfix">
                            <div class="table-responsive">
                                <table class="table user-list">
                                    <thead>
                                        <tr>
                                            <th><span>Username</span></th>
                                            <th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                                <%
                                                    try{
                                                    Statement statement2;
                                                    Connection connection2;
                                                    connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","watermelon123");
                                                    statement2 = connection2.createStatement();
                                                    ResultSet rs =statement2.executeQuery("select * from users");
                                                    while(rs.next()){
                                                %>
                                        <tr>
                                            <td>
                                                <%= rs.getString("username") %>
                                            </td>
                                            <td>
                                                <a href="#" class="btn btn-primary pull-right">Edit</a>
                                                <% if (!rs.getString("username").equals(session.getAttribute("user"))){ %>
                                                   <a href="#" class="btn btn-primary pull-right">Delete</a>
                                                <%}%>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%
                                        rs.close();
                                        statement2.close();
                                        connection2.close();
                                    }
                                    catch(Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>

    <!-- global scripts -->
    <script src="js/userList/jquery.js"></script>
    <script src="js/userList/bootstrap.js"></script>
    <script src="js/userList/scripts.js"></script>

</body>

</html>
