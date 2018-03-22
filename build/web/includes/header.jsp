<%-- 
    Document   : header
    Created on : Dec 3, 2016, 7:09:47 AM
    Author     : Amee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Buy</title>
        
        
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
    
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
          <link rel="shortcut icon" href="<c:url value='/Images/icon.ico'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/> ">
    </head>
    <header>
        <div class="row" style="height: 90px;">
            <div class="col-lg-2 col-md-2 col-xs-2">
                <img src="<c:url value='/Images/Logo.jpg'/>" alt="Book Buy Logo" class="iconDetails">
            </div>
            <div class="col-lg-5 col-md-5 col-xs-5" style="padding-top: 15px;">
                <p class="h1">Book Buy</p>
            </div>
        </div>
        
    <%--    <h3>"A good bookshop is just a genteel Black Hole that knows how to read."</h3>
        <h4>- Terry Pratchett, Guards! Guards!</h4> --%>
    </header>
             <ul class="topnav" id="myTopnav">
  <li><a href="<%=request.getContextPath()%>/Home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/BrowseBooks">Browse Catalog</a></li>
<li><a href="<%=request.getContextPath()%>/MyOrders">My Orders</a></li>
  <li><a href="<%=request.getContextPath()%>/MyCart">Cart</a></li>
   <li><a href="<%=request.getContextPath()%>/admin">Admin</a></li>
  <li><a href="<%=request.getContextPath()%>/Login">Customer Login</a></li>
  <li><a href="<%=request.getContextPath()%>/JS/contactUS.jsp">Contact Us</a></li>
    <li class="icon">
    <a href="javascript:void(0);" onclick="myFunction()">&#9776;</a>
  </li>
</ul>
