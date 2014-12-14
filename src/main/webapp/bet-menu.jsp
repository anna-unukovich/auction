<%@page import="java.util.ArrayList"%>
<%@page import="com.unukovich.auction.model.Auction"%>
<%@page import="com.unukovich.auction.model.Auction"%>
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

        <title>Bet's menu page</title>
    </head>
    <body>
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

                %>
                <%=login%>

            </h4>
            <br>


            <table class="table table-striped">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Current price</th>
                    <th>Bet</th>
                </tr>
                <%
                    AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");
                    List<Auction> auctionsList = new ArrayList<>();
                    auctionsList = auctionService.getAllAuctions();

                    for (int i = 0; i < auctionsList.size(); i++) {
                        Auction auction = auctionsList.get(i);
                        if (auction != null) {
                            out.write("<tr>");
                            out.write("<td>" + auction.getName() + "</td>");
                            out.write("<td>" + auction.getDescription() + "</td>");
                            out.write("<td>" + auction.getCurrentPrice()+ "</td>");
                            
                            int finish = auction.getIsFinal();                            
                            if (finish != 0) {
                            out.write("<td>" + "FINISHED!" + "</td>");
                            }
                            
                            if (user.getId() != auction.getCreatorId()){
                            
                            out.write("<td>" 
                                    + "<form method=\"GET\" action=\"bet.jsp\" >"  
                                    + "<input type=\"hidden\" name=\"auctionId\" value=\""+ auction.getId() +"\" />"
                                    + "<button class=\"btn btn-info\" type=\"submit\">"
                                    + "<span class=\"glyphicon glyphicon-plus\"></span> Create bet"
                                    + "</button>"
                                    + "</form>                                    "
                                    + "</td>");
                            } else {
                            out.write("<td>" + "OWNER" + "</td>");    
                            }
                            
                            out.write("</tr>");
                        } else {
                            out.write("<tr>");
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
