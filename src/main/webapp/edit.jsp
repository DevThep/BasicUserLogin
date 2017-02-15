<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit User</title>

    <!-- favicon -->
    <link rel="icon" href="images/transformer.ico">

    <!-- Bootstrap -->
    <script src="js/pace.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link href="css/mystyle.css" rel="stylesheet">


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
    <div class="container" id="container" style="display:none;">
        <section id="form">
            <div class="container">
                <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel white-alpha-90">
                        <div class="panel-heading">
                            <div class="panel-title text-center">
                                <h2>Edit <span class="text-primary">${userEdit}</span></h2></div>
                        </div>
                        <div class="panel-body">
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginform" class="form-horizontal" role="form">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input id="login-username" type="text" class="form-control" name="name" value=${userEdit} placeholder="username">
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input id="firstname" type="text" class="form-control" name="name" value="${fname}" placeholder="firstname">
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input id="lastname" type="text" class="form-control" name="name" value="${lname}"s placeholder="lastname">
                                </div>
                                <div class="input-group center">
                                  <span id="btn-login" style="border-left: 0px"><a href="#" class="btn btn-success">Edit</a></span>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
                <div class="container text-center">
                    <div class="footer-content">
                        <a href="/login" class="btn btn-info"> Back </a>
                    </div>
                    <div class="footer-content">
                        <a href="logout.jsp" class="btn btn-primary"> Sign Out </a>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </nav>
        </footer>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.backstretch.min.js"></script>

    <script>
        Pace.on('hide', function() {
            $("#container").fadeIn('0');
            $.backstretch([
                "images/buildings.jpg",
                "images/paris_night.jpg"
            ], {
                duration: 5000,
                fade: 1000
            });
        });
    </script>
</body>

</html>
