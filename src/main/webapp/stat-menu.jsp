<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unukovich.auction.model.Stat"%>
<%@page import="java.util.List"%>
<%@page import="com.unukovich.auction.service.StatService"%>
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

        <title>Statistic menu page</title>
    </head>
    <body>
        <div class="container">
            <h3>Statistic menu</h3>
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

            <table class="table table-striped">
                <tr>
                    <th>Stat id</th>
                    <th>Date</th>
                    <th>Description</th>
                </tr>
                <%
                    StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
                    List<Stat> statsList = new ArrayList<>();
                    statsList = statService.getAllStats();

                    for (int i = 0; i < statsList.size(); i++) {
                        Stat stat = statsList.get(i);
                        if (user != null) {
                            out.write("<tr>");
                            int statId = stat.getId();
                            out.write("<td>" + statId + "</td>");

                            Date statDate = user.getRegistrationDate();
                            if (statDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                                String date = sdf.format(statDate);
                                out.write("<td>" + date + "</td>");
                            } else {
                                out.write("<td>" + "</td>");
                            }

                            String statDescription = stat.getDescription();
                            out.write("<td>" + statDescription + "</td>");

                            out.write("</tr>");
                        }

                        out.write("");
                    }
                %>

            </table>
            <br>

            <form action="main-menu.jsp">
                <button class="btn btn-info" type="submit">
                    <span class="glyphicon glyphicon-home"></span> Back to main menu
                </button>
            </form>
        </div>
    </body>
</html>
