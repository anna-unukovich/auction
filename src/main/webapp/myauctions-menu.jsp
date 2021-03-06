<%@page import="com.unukovich.auction.model.Auction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
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

        <title>My auction's menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Online auction's main menu</h3>
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
            
            
            <table class="table table-striped">
                <tr>
                    <th>Auction id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start price</th>
                    <th>Created date</th>
                    <th>Is finished</th>
                </tr>
<%
                    AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");
                    List<Auction> auctionsList = new ArrayList<>();
                    auctionsList = auctionService.getAllAuctions();
                    
                    List<Auction> currentUserAuctionsList = new ArrayList<>();
                    for (int i = 0; i < auctionsList.size(); i++) {
                        Auction auction = auctionsList.get(i);
                        if (user.getId() == auction.getCreatorId()){
                            currentUserAuctionsList.add(auction);
                        }
                    }

                    for (int i = 0; i < currentUserAuctionsList.size(); i++) {
                        Auction auction = currentUserAuctionsList.get(i);
                        if (auction != null) {
                            out.write("<tr>");
                            out.write("<td>" + auction.getId() + "</td>");
                            out.write("<td>" + auction.getName() + "</td>");
                            out.write("<td>" + auction.getDescription() + "</td>");
                            out.write("<td>" + auction.getStartPrice() + "</td>");
                            out.write("<td>" + auction.getCreatedDate() + "</td>");
                            int finish = auction.getIsFinal();
                            String finishString = "not";
                            if (finish != 0) {
                                finishString = "yes";
                            } 
                            out.write("<td>" + finishString + "</td>");
                            out.write("</tr>");
                        } else {
                        out.write("<tr>");
                        out.write("<td> </td>");
                        out.write("<td> </td>");
                        out.write("<td> </td>");
                        out.write("<td> </td>");
                        out.write("<td> </td>");
                        out.write("<td> </td>");
                        out.write("</tr>");
                        }

                        out.write("");
                    }
                %>
            </table>
            
           
            <table> 
                <tr>
                    <td>
                        <form action="auction-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Add auction
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="main-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> Back to main menu
                            </button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
