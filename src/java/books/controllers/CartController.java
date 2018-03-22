/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.controllers;

import books.Data.CartIO;
import books.Data.ConnectionPool;
import books.Data.DBUtil;
import books.Data.OrderIO;
import books.business.Cart;
import books.business.CheckoutItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import books.util.*;
import java.util.HashSet;

/**
 *
 * @author Amee
 */
@WebServlet(name = "CartController", urlPatterns = {"/MyCart", "/RemoveCart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
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
        HttpSession session = request.getSession(false);
         String url;
        if(session.getAttribute("user")==null)
        {
         url="/Login";   
        }
        else
        {
        String userID = session.getAttribute("user").toString();
        List<CheckoutItem> checkoutItemList = CartController.getCheckoutItems(request, userID);
        request.setAttribute("checkoutItemList", checkoutItemList);
      
          url = "/JS/cart.jsp";
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
        String action = request.getParameter("submit");
        HttpSession session = request.getSession(false);
        String userId = "";
        double totalPrice = 0d;
        String url = request.getContextPath() + "/MyCart";
        if (session.getAttribute("user") != null){
            userId = session.getAttribute("user").toString();
        }
        if (session.getAttribute("totalPrice") != null){
            totalPrice = Double.parseDouble(session.getAttribute("totalPrice").toString());
        }
        if (action.equals("RemoveCart")){
            int cartId = Integer.parseInt(request.getParameter("removeItem"));
            CartIO.deleteFromCart(cartId);
            
            
            response.sendRedirect(url);
            /*String url = "/MyCart";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);*/
        }else if (action.equals("RaiseOrder")){
         //   url = request.getContextPath() + "/MyOrders";
           int orderId= OrderIO.createOrder(userId, totalPrice);
           boolean mailsuccess=sendEmail(userId, orderId);
           if(mailsuccess){
               request.setAttribute("orderID", orderId);
               url="/JS/thanks.jsp";
              RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);  
           }
        }
        
        
        return;
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

    public static List<CheckoutItem> getCheckoutItems(HttpServletRequest request, String userId){
        List<CheckoutItem> returnList = new ArrayList<CheckoutItem>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        String query = "SELECT b.bookid, a.authorname, b.title, c.quantity, c.quantity*b.price totalPrice, c.cartid"
                + " from books b, authors a, cart c"
                + " where b.authorID = a.authorID AND b.bookId = c.bookId"
                + " and c.userId = ?";
        
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            rs = statement.executeQuery();
            double totalPrice = 0.00d;
            while(rs.next()){
                CheckoutItem item = new CheckoutItem();
                item.setAuthor(rs.getString("authorname"));
                item.setBookId(rs.getInt("bookid"));
                item.setBookTitle(rs.getString("title"));
                item.setPrice(rs.getDouble("totalPrice"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUserId(userId);
                item.setCartId(rs.getInt("cartid"));
                totalPrice += rs.getDouble("totalPrice");
                returnList.add(item);
            }
            
            HttpSession session = request.getSession(false);
            session.setAttribute("totalPrice", totalPrice);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return returnList;
    }
    
    public static boolean sendEmail(String userID, int orderID){
          ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String useremail="";
        String firstname="";
        String lastname="";
        try{
        String query = "SELECT firstname, lastname, email from users where userid=?";
       PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            rs = statement.executeQuery();
            while(rs.next()){
                useremail=rs.getString("email");
                firstname=rs.getString("firstname");
                lastname=rs.getString("lastname");
            }
             try {
            String to = useremail;
            String from = "orderManagement@bookbuy.com";
            String subject = "Your order with Order Number: "+orderID+" has been raised";
            String body = "Dear " + firstname + ",\n\n"
                    + "Thanks for placing an order with BookBuy. We'll make sure to send "
                    + "your order as soon as we can.\n"
                    + "Your Order Number is: " + orderID +
                     "\n\n Best Regards \n"
                    + "BookBuy.com";
            boolean isBodyHTML = false;
            
                MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
                return true;
            } catch (MessagingException e) {
               e.printStackTrace();
                
                return false;
            }
              }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return true;
    }
}
