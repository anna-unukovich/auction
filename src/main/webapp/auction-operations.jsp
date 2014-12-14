<%@page import="com.unukovich.auction.model.User"%>
<%@page import="com.unukovich.SpringFactory"%>
<%@page import="com.unukovich.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/auction.css" rel="stylesheet">

        <title>Auction operations page</title>
    </head>
    <body>
<div class="container">
            <h3>Users operations menu</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    String login = "";
                    if (user != null) {
                        login = user.getName();

                        String admin = user.getAdmin();
                        login += " (" + admin + ")";
                    }

                %>
                <%=login%>

            </h4>
            <br>
            <form class="form-signin" method="GET" action="/auction/webresources/auctions/create">
            <input name = "paramName" type="text" id="name" class="form-control" placeholder="name">                          
            <br>
            <input name = "paramDescription" type="text" id="password" class="form-control" placeholder="description">
            <br>
            <input name = "paramStartPrice" type="text" id="role" class="form-control" placeholder="start price">
            <br>
            <br>
            
            <table>
                <tr>
                    <td>
                        
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-plus"></span> Create new auction
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>

                    <td>
                        <form action="user-menu.jsp">
                            <button class="btn btn-info">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Back to my auction's menu
                            </button>
                            </form>
                    </td>
                </tr>
            </table>    

        </form>
    </div>
    </body>
</html>
