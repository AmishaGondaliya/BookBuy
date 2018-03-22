<%-- 
    Document   : cart
    Created on : Dec 3, 2016, 8:27:23 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="<%=request.getContextPath()%>/MyOrder" method="post">
  <table class="table table-condensed" style="margin-top: 20px; font-size: 16px;">
    <thead>
      <tr>
        <th>Order ID</th>
        <th>Price</th>
        <th>Order Date</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${orderList}" var="item">
          <tr>
              <td>${item.orderID}</td>
              <td>${item.orderTotal}</td>
              <td>${item.orderDate}</td>
              
          </tr>
          
      </c:forEach>
      
    
    </tbody>
  </table>
    
</form>

