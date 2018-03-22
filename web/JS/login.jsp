<%-- 
    Document   : Login
    Created on : Dec 2, 2016, 7:21:26 PM
    Author     : Amee
--%>

<jsp:include page="/includes/header.jsp" />
<p class="message">${message}</p>
<section class="login">
<p class="h2">Login Form<p>

<p class="h5">Please enter a username and password to continue.</p>
<form action="<%=request.getContextPath()%>/Login" method="post">
    <div class="row form-group">
        <div class="col-lg-1 col-md-1 col-sm-1">
           <label>Username: </label> 
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="text" name="username">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-1 col-md-1 col-sm-1">
           <label>Password: </label>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3">
           <input type="password" name="password">
        </div>
    </div>
    
    <div class="row form-group">
        <div class="col-lg-2 col-md-1 col-sm-1">
            <button class="btn-success btn-sm" type="submit" name="submit" value="Login"
                    style="width: 100%;">Login</button>
        </div>
    </div>
    
</form>
    <div class="row">
        <div class="col-lg-2 col-md-1 col-sm-1 h5">
            <a href="<%=request.getContextPath()%>/Register" style="width: 100%;">Register</a>
        </div>
    </div>
    
    
</section>
    
