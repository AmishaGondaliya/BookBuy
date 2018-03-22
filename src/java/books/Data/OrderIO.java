/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.Data;

import java.sql.Date;
import books.business.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Amee
 */
public class OrderIO {
    
     public static List<Orders> selectOrders(String UserID)
     {
          List<Orders> Orders = new ArrayList<Orders>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
         PreparedStatement ps = null;
         ResultSet rs = null;
        try{
        String query = "Select o.ORDERID, o.ORDERDATE, o.ORDERTOTAL from ORDERS o where USERID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, UserID);
            rs = statement.executeQuery();
            
             
            while(rs.next()){
                
                int OrderID = rs.getInt("ORDERID");
                double OrderTotal = rs.getDouble("ORDERTOTAL");
                Date OrderDate=rs.getDate("ORDERDATE");
                
               
               Orders order=new Orders();
               order.setOrderDate(OrderDate);
               order.setOrderTotal(OrderTotal);
               
               order.setOrderID(OrderID);
               
               Orders.add(order);
            }
            connection.close();
            
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
         
        return Orders;
     
    }
    
     public static int createOrder(String UserID, double OrderTotal){
         
          ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
         PreparedStatement ps = null;
         ResultSet rs = null;
        try{
        String query = "Select CartID, USERID, BOOKID, Quantity from Cart where userID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, UserID);
            rs = statement.executeQuery();
            List<Cart> Carts=new ArrayList<Cart> ();
            while(rs.next()){
                
                Cart cart=new Cart();
               cart.setBookID(rs.getInt("BookID"));
               cart.setCartID(rs.getInt("CARTID"));
               cart.setQuantity(rs.getInt("Quantity"));
               Carts.add(cart);
            }
            
            query = "SELECT MAX(ORDERID) ORDERID FROM ORDERS";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            int orderId = 0;
            while (rs.next()){
                orderId = rs.getInt("ORDERID");
                orderId++;
            }
            
            query="Insert into Orders(USERID,ORDERDATE,ORDERTOTAL, ORDERID) values(?,?,?, ?)";
            statement=connection.prepareStatement(query);
            statement.setString(1,UserID);
            statement.setDate(2, new Date(new java.util.Date().getTime()));
            statement.setDouble(3, OrderTotal);
            statement.setInt(4, orderId);
            
            statement.executeUpdate();
            
            for (int i=0; i<Carts.size(); i++){
                Cart cart = Carts.get(i);
                query = "INSERT INTO ORDERDETAILS(ORDERID, USERID, QUANTITY, BOOKID) VALUES(?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, orderId);
                statement.setString(2, UserID);
                statement.setInt(3, cart.getQuantity());
                statement.setInt(4, cart.getBookID());
                
                statement.executeUpdate();
            }
            
            query = "DELETE FROM CART WHERE USERID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, UserID);
            statement.executeUpdate();
            
            return orderId;
            
            
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        return 0;
            
     }
}
    

