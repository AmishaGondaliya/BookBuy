/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.Data;
import books.business.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Amee
 */
public class BooksIO {
    
     public static void addBook(Books book){
         
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            
        
          String query
                = "INSERT INTO Books ( title, isbn, genre,type,publicationYear,price,bookCondition,quantity "
                    + "VALUES (?, ?, ?, ?, "
                + "?, ?, ?, ?";
          ps=connection.prepareStatement(query);
          ps.setString(1, book.getTitle());
          ps.setString(2,book.getISBN());
          ps.setString(3,book.getGenre());
          ps.setString(4,book.getType());
          ps.setInt(5, book.getPublicationYear());
          ps.setDouble(6,book.getPrice());
          ps.setString(7,book.getCondition());
          ps.setInt(8, book.getQuantity());
          ps.executeUpdate();
            connection.close();
            
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }
        
     }
     
     public static List<Books> selectBooks(){ 
        
        List<Books> booklist = new ArrayList<Books>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        String query = "Select b.BookID, Title, authorname, ISBN, GENRE, TYPE, PublicationYear, Price, Bookcondition from Books b, authors a where b.authorID=a.authorID";
            PreparedStatement statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            
             
            while(rs.next()){
                String Title = rs.getString("Title");
                String authorname = rs.getString("authorname");
                double price = rs.getDouble("Price");
                String ISBN=rs.getString("ISBN");
                String GENRE=rs.getString("GENRE");
                String Type=rs.getString("Type");
                int bookId = rs.getInt("BookID");
                int PublicationYear=rs.getInt("PublicationYear");
                String BookCondition=rs.getString("BookCondition");
                Books book = new Books();
                book.setTitle(Title);
                book.setAuthorName(authorname);
                book.setISBN(ISBN);
                book.setBookID(bookId);
                book.setPrice(price);
                book.setPublicationYear(PublicationYear);
                book.setGenre(GENRE);
                book.setType(Type);
                book.setCondition(BookCondition);
                
                booklist.add(book);
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
         
        return booklist;
     
    }
         
     }
     

