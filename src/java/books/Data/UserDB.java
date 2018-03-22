/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.Data;

/**
 *
 * @author Amee
 */
import java.sql.*;

import books.business.*;
import books.util.PasswordUtil;
import java.security.NoSuchAlgorithmException;

public class UserDB {

    public static void createUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO Users ( FirstName, LastName, Email, "
                + "USER_ADDR_LN1, USER_ADDR_LN2, UserID, Pwd, Salt,usercategory, zip, city, state,"
                +"country, cctype, ccnumber, ccexpiration) "
                    + "VALUES (?, ?, ?, ?, "
                + "?, ?, ?, ?,'C',?,?,?,?,?,?,?)";
        try {
            String salt = PasswordUtil.getSalt();
            String hashedPassword = "";
            try{
                hashedPassword = PasswordUtil.hashAndSaltPassword(user.getPwd(), salt);
            }catch(NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getId());
            ps.setString(7, hashedPassword);
            ps.setString(8, salt);
          
   ps.setString(9,user.getZip());
   ps.setString(10,user.getCity());
   ps.setString(11, user.getState());
   ps.setString(12, user.getCountry());
   ps.setString(13,user.getCreditCardType());
   ps.setString(14, user.getCreditCardNumber());
   ps.setString(15, user.getCreditCardExpirationDate());
            
            ps.executeUpdate();
            
            //Get the user ID from the last INSERT statement.
            /*String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            String userID = identityResultSet.getString("IDENTITY");
            identityResultSet.close();
            identityStatement.close();*/

            // Set the user ID in the User object
            //user.setId(userID);
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

   
    public static User selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("UserID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setCountry(rs.getString("Country"));
                user.setCreditCardType(rs.getString("CreditCardType"));
                user.setCreditCardNumber(rs.getString("CreditCardNumber"));
                user.setCreditCardExpirationDate(rs.getString("CreditCardExpirationDate"));
            }
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    public static boolean useridExists(String userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
          PreparedStatement ps = null;
        ResultSet rs = null;
            String query = "SELECT UserID FROM User "
                + "WHERE UserID = userID";
               try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
               }
    }
    
    public static User retrieveSaltandHashedPassword(String userName){
        User user = new User();
        user.setUserId(userName);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT SALT, PWD FROM Users "
                + "WHERE UserID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String salt = rs.getString("SALT");
                String hashedPassword = rs.getString("PWD");
                
                user.setPwd(hashedPassword);
                user.setSalt(salt);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }
    public static boolean checkUserCategoryA(String userID){
          String userCategory="";
       
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT usercategory FROM Users "
                + "WHERE UserID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 userCategory = rs.getString("usercategory");
                
            }
            
            if(userCategory.equals("A")){
                return true;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return false;
    }
}
