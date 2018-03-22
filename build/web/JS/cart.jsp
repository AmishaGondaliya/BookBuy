<%-- 
    Document   : cart
    Created on : Dec 3, 2016, 8:27:23 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="<%=request.getContextPath()%>/RemoveCart" method="post">
<table class="table table-condensed" style="margin-top: 20px; font-size: 16px;">
    <thead>
      <tr>
        <th>Book Title</th>
        <th>Author</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Remove</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${checkoutItemList}" var="item">
          <tr>
              <td>${item.bookTitle}</td>
              <td>${item.author}</td>
              <td>${item.quantity}</td>
              <td>${item.price}</td>
              <td><button name="submit" onclick="setRemoveItem(${item.cartId})" value="RemoveCart"
                    "style="margin-left: 20%;"><i class="fa fa-minus-circle removeIcon"></button></td>
              
          </tr>
          
      </c:forEach>
      <input type="hidden" id="removeItem" value="" name="removeItem"> 
          <tr>
              <td style="text-align: right;" colspan="4"><b>Total Price: </b></td>
              <td><b><%= session.getAttribute("totalPrice") %></b></td>
          </tr>
    
    </tbody>
  </table>
    <div class="row form-group">
        <div class="col-lg-2 col-md-1 col-sm-1">
            <button class="btn-success btn-sm" type="submit" name="submit" value="RaiseOrder"
                    style="width: 100%;">Submit</button>
        </div>
    </div>
</form>
          
    
          
<script>
    function setRemoveItem(cartId){
        $('#removeItem').val(cartId);
    }
</script>
