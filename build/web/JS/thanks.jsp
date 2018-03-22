<%-- 
    Document   : thanks
    Created on : Dec 4, 2016, 11:57:38 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />

<h1>Thankyou for your order.</h1>
<h2>Your Order Number is ${orderID}.</h2>
<a href="<%=request.getContextPath()%>/BrowseBooks">Click here</a>
<h2>to continue shopping</h2>