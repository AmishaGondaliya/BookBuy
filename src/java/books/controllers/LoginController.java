/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.controllers;
import books.Data.UserDB;
import books.business.*;
import books.util.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amee
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Login","/Register","/Home"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 /*   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
     /*       out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>"); */
  /*   String url = "/JS/Login.jsp";
         RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  //      processRequest(request, response);
         String requestURI = request.getRequestURI();
      String url = "/JS/login.jsp";
        if (requestURI.endsWith("/Register"))
        {
               url = "/JS/register.jsp";
        }
            HttpSession session1=request.getSession(false);
         if(requestURI.endsWith("/Home"))
         {
              url="/JS/home.jsp";
             if(session1.getAttribute("user") != null)
             {
                 if(session1.getAttribute("Superuser")=="su")
                 {
                    url="/admin/adminhome.jsp";
                 }
             }
             
         }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
         
         
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  //      processRequest(request, response);
          String requestURI = request.getRequestURI();
         String action = request.getParameter("submit");
    
         if (action.equals("Register"))
         {
            
             String Username=request.getParameter("username");
                String Pwd=request.getParameter("password");
                String RetypedPassword=request.getParameter("retypepassword");
             String FirstName=request.getParameter("FirstName");
             String Email=request.getParameter("EmailId");
             String LastName=request.getParameter("LastName");
             String addressLine1=request.getParameter("address1");
             String addressLine2=request.getParameter("address2");
             String city=request.getParameter("city");
             String state=request.getParameter("state");
             String zip=request.getParameter("zip");
             String country=request.getParameter("country");
             String cctype=request.getParameter("cctype");
             String ccnumber=request.getParameter("ccnumber");
             String ccexpiration=request.getParameter("ccexpiration");
             boolean emailExists=UserDB.emailExists(Email);
             
            
             boolean useridExists=UserDB.useridExists(Username);
             
             if((emailExists==false) && (useridExists==false)){
              User user=new User();
              user.setId(Username);
              user.setpwd(Pwd);
              user.setFirstName(FirstName);
              user.setLastName(LastName);
              user.setEmail(Email);
              user.setAddress1(addressLine1);
              user.setAddress1(addressLine2);
              user.setCity(city);
              user.setState(state);
              user.setZip(zip);
              user.setCountry(country);
              user.setCreditCardType(cctype);
              user.setCreditCardNumber(ccnumber);
              user.setCreditCardExpirationDate(ccexpiration);
                 UserDB.createUser(user);
                 String url = request.getContextPath() + "/Login";
                 response.sendRedirect(url);
                 return;
             }
         }else if (action.equals("Login")){
             String userName = request.getParameter("username");
             String password = request.getParameter("password");
             
             User user = UserDB.retrieveSaltandHashedPassword(userName);
             
             try{
                String hashedPassword = PasswordUtil.hashAndSaltPassword(password, user.getSalt());
                 String url="";
                if (hashedPassword.equals(user.getPwd())){
                  System.out.println("Password Matched");
                  HttpSession session=request.getSession(false) ;
                  session.setAttribute("user", user.getId());
                  boolean userCategory=UserDB.checkUserCategoryA(userName);
                  
                  if (userCategory){
                      session.setAttribute("Superuser", "su");
                       url = "/admin/adminhome.jsp";
                       RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
                  }
                  /*String url = "/JS/catalog.jsp";
                  RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                  dispatcher.forward(request, response);*/
                  
   //               String url = request.getContextPath() + "/BrowseBooks";
                  else{
     url = request.getContextPath() + "/JS/home.jsp";
                  response.sendRedirect(url);
                  return;
                  }
                }else{
                    request.setAttribute("message", "Incorrect Creds, Please try again");
                    url = "/JS/login.jsp";
                       RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
                    System.out.println("Password Did not Match");
                }
             }catch(NoSuchAlgorithmException e){
                 e.printStackTrace();
             }
         }
          
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
