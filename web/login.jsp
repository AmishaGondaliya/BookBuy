<%-- 
    Document   : login
    Created on : Dec 5, 2016, 7:50:58 PM
    Author     : Amee
--%>
<jsp:include page="/includes/header.jsp" />



    <p><label class="adminloginheading">Login Form</label></p>
<p><label class="adminloginlabel">Please enter a username and password to continue.</label></p>
<form action="j_security_check" method="post">
    <p>    <label class="adminloginlabel">Username</label>
    <input type="text" name="j_username" class="adminlogintext"><br>
    </p>
    <p>
    <label class="adminloginlabel" >Password</label>
    <input type="password" name="j_password" class="adminlogintext"><br>
    
    
    <label>&nbsp;</label>
    </p>
    <p>
        <div class="divpadding">
    <input type="submit" value="Login">
        </div>
    </p>
</form>

</section>

