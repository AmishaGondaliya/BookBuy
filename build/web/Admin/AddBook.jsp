<%-- 
    Document   : AddBook
    Created on : Dec 5, 2016, 9:27:33 PM
    Author     : Amee
--%>
<jsp:include page="/includes/header.jsp" />
<p class="booksectionheader">Add Book</p>
<form action="<%=request.getContextPath()%>/admin/AddBook" method="post">
    
<section>
    <p>
    <label>Book Name: </label>
    <input type="text" name="bookName">
    </p>
    <p>
    <label>Author Name: </label>
      <input type="text" name="authorName">
    </p>
    <p>
    <label>ISBN: </label>
      <input type="text" name="ISBN">
    </p>
    <p>
    <label>Genre: </label>
      <input type="text" name="genre">
    </p>
    <p>
    <label>Type: </label>
      <input type="text" name="type">
    </p>
    <p>
    <label>Publication Year: </label>
      <input type="text" name="publicationYear">
      </</p>
    <p>
              <label>Price: </label>
                <input type="text" name="price">
    </p>
    <p>
          <label>Condition: </label>
            <input type="text" name="condition">
    </p>
    <p>
         <label>Quantity: </label>  
           <input type="text" name="quantity">
           
    </p>
    <p>
        <input type="submit" value="Add" name="addBook">
    </p>
</section>
</form>
