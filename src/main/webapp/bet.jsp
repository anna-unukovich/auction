<%@page import="java.util.ArrayList"%>
<%@page import="com.unukovich.auction.model.Bet"%>
<%@page import="java.util.List"%>
<%@page import="com.unukovich.auction.service.BetService"%>
<%@page import="com.unukovich.auction.model.Auction"%>
<%@page import="com.unukovich.auction.service.AuctionService"%>
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

        <title>Bet page</title>
    </head>
    <body>
        <%
            String auctionId = request.getParameter("auctionId");
        %>
        <div class="container">
            <h3>Bet's menu</h3>
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

                    AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");
                    Auction auction = auctionService.readAuction(Integer.parseInt(auctionId));
                    int auctionIdInt = auction.getId();
                    String auctionName = auction.getName();
                    String auctionDescription = auction.getDescription();
                %>
                <%=login%>

            </h4>
            <br>
            <h5>Auction id: <%=auctionId%></h5>
            <h5>Auction name <%=auctionName%></h5>
            <h5>Auction description: <%=auctionDescription%></h5>
            <br>

            <table class="table table-striped">
                <tr>
                    <th>bet id</th>
                    <th>price</th>
                    <th>user_id</th>
                </tr>
                <%
                    BetService betService = (BetService) SpringFactory.getspringApplicationContext().getBean("betService");
                    List<Bet> betsList = new ArrayList<>();
                    betsList = betService.getAllBets();

                    List<Bet> auctionBetsList = new ArrayList<>();
                    // remove bets not for this auction.
                    for (int i = 0; i < betsList.size(); i++) {
                        Bet tempBet = betsList.get(i);
                        if (auctionIdInt == tempBet.getAuction_id()) {
                            auctionBetsList.add(tempBet);
                        }
                    }

                    for (int i = 0; i < auctionBetsList.size(); i++) {
                        Bet bet = betsList.get(i);

                        out.write("<tr>");
                        out.write("<td>" + bet.getId() + "</td>");
                        out.write("<td>" + bet.getPrice() + "</td>");
                        out.write("<td>" + bet.getUser_id() + "</td>");
                        out.write("</tr>");
                    }
                %>
            </table>

            <br>
            <table class="table table-striped">
                <form class="form-signin" method="GET" action="/auction/webresources/bets/create">
                    <input type="hidden" name="auctionId" value="<%=auctionId%>"> 
                    <tr>
                        <td>
                            <input name = "paramPrice" type="text" id="login" class="form-control" placeholder="price">
                        </td>
                        <td>
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-plus"></span> Create new bet
                            </button>
                        </td>
                    </tr>
                </form>
                <tr>
                    <td></td>
                    <td>    
                        <form action="bet-menu.jsp">
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Back to bet's menu
                            </button>
                        </form>
                    </td>
                </tr>
            </table>

        </div>
    </body>
</html>
