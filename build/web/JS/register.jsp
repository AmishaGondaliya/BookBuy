<%-- 
    Document   : Register
    Created on : Dec 2, 2016, 11:44:04 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />
</body>
<section>

<p class="h2">Register</p>

<form action="<%=request.getContextPath()%>/Register" method="post">
    <div class="divregister">
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Username: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="username">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Password: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="password" name="password"><br>
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Retype-Password: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="password" name="retypepassword">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Email: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="EmailId">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>First Name: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="FirstName">
        </div>
    </div>
    
   <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Last Name: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="LastName">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Address Line 1: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="address1">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Address Line 2: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="address2">
        </div>
    </div>
     <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>City : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="city">
        </div>
    </div>
         <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>State : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="state">
        </div>
    </div>
    </div>
         <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Zip : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="zip">
        </div>
    </div>
        <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Country : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="country">
        </div>
    </div>
         <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Credit Card Type : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="cctype">
        </div>
    </div>
          <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Credit Card Number : </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="ccnumber">
        </div>
    </div>
             <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
           <label>Credit Card Expiration Date (MM/YYYY): </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="ccexpiration">
        </div>
    </div>
</div>
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2">
            <button class="btn-success btn-sm" type="submit" name="submit" value="Register"
                    style="width: 100%;">Register</button>
        </div>
    </div>
    
</form>
  
</section>
</body>
</html>