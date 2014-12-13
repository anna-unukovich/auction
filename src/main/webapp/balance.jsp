<%@page import="java.util.ArrayList"%>
<%@page import="com.unukovich.auction.model.Balance"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.unukovich.auction.service.BalanceService"%>
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

        <title>Balance menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Balance menu</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    int userId = 0;
                    String login = "";
                    if (user != null) {
                        user.getId();

                        login = user.getName();

                        String admin = user.getAdmin();
                        login += " (" + admin + ")";

                    }

                    BalanceService balanceService = (BalanceService) SpringFactory.getspringApplicationContext().getBean("balanceService");
                    List<Balance> balancesList = new ArrayList<>();
                    balancesList = balanceService.getAllBalances();

                    int balanceUserId = 0;
                    String regDate = "";
                    int userBalance = 0;
                    for (int i = 0; i < balancesList.size(); i++) {
                        Balance balance = balancesList.get(i);
                        if (balance != null) {
                            if (balance.getUserId() == user.getId()) {
                                balanceUserId = balance.getUserId();
                                regDate = balance.getRegistrationDate().toString();
                                userBalance = balance.getBalance();
                            }
                        }
                    }

                %>
                <%=login%>
            </h4>
            <br>

            <table class="table table-striped">
                <tr>
                    <th>Registration date</th>
                    <th>Balance</th>
                </tr>
                <tr>Description
                    <td><%=regDate%></td>
                    <td><%=userBalance%></td>
                </tr>
            </table>


            <form action="main-menu.jsp">
                <button class="btn btn-info" type="submit">
                    <span class="glyphicon glyphicon-home"></span> Back to main menu
                </button>
            </form>
        </div>
    </body>
</html>
