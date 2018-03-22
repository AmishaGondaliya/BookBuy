/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.Data;
import books.business.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author Amee
 */
public class CartIO {
    
    public static List<Cart> RetrieveProductsFromCart(String UserID){
        List<Cart> CartList=new ArrayList<Cart> ();
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        String query = "Select CartID, UserID, BookID, Quantity from Cart where UserID=?";
        
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, UserID);
            rs = statement.executeQuery();
              while(rs.next()){
              int CartID=rs.getInt("CartID");
              UserID=rs.getString("UserID");
              int BookID=rs.getInt("BookID");
              int quantity=rs.getInt("Quantity");
              Cart cartobj=new Cart();
              cartobj.setBookID(BookID);
              cartobj.setCartID(CartID);
              cartobj.setUserID(UserID);
              cartobj.setQuantity(quantity);
              CartList.add(cartobj);
              }
             
              
           connection.close();
            
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }
        return CartList;
    
    }

    public static void insertProductsinCart(Cart cartE){
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        String query="Insert into Cart (UserID, BookID, Quantity) values (?,?, ?)";
          ps = connection.prepareStatement(query);
            ps.setString(1, cartE.getUserID());
            ps.setInt(2, cartE.getBookID());
            ps.setInt(3, cartE.getQuantity());
            ps.executeUpdate();
            
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }
    }
    
    public static void deleteFromCart(int cartId){
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         try{
        String query="Delete from Cart where CartID=?";
          ps = connection.prepareStatement(query);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }finally {
            
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
