<%-- 
    Document   : catalog
    Created on : Dec 3, 2016, 3:37:50 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />
<%@ page import="java.util.List" %>
<%@ page import="books.business.*" %>
<%@ page import="books.Data.*" %>

<%@ page import= "javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<%=request.getContextPath()%>/BrowseBooks" method="post">
    <div class="row" style="margin-top: 40px">
        <input type="hidden" name="bookId" id="bookId" value="">  
        <c:forEach items="${Booklist}" var="book">
            
            <div class="col-lg-3 col-md-3 col-sm-3">
                <img src="<c:url value='/Images/book-flat.png'/>" alt="Book Buy Logo" class="bookIcon">
                <div style="margin-top: 170px; font-size: 14px">
                    <div>${book.title}</div>
                    <div>Price: $${book.price}</div>
                    <div>Author: ${book.authorName}</div><br>
                    <button onclick="addToCart('${book.bookID}')" class="btn-info btn-md">
                        <i class="fa fa-shopping-cart"></i>   Add to Cart
                    </button>
                </div>
            </div>

        </c:forEach>
    </div>
</form>

    <script>
        function addToCart(bookId){
            $('#bookId').val(bookId);
        }
    </script>