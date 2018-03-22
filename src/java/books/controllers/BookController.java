/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.controllers;
import books.business.*;
import java.util.*;
import books.Data.BooksIO;
import books.Data.CartIO;
import books.Data.ConnectionPool;
import books.Data.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "BookController", urlPatterns = {"/BrowseBooks"})
public class BookController extends HttpServlet {

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
        /*    out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(false);
        List<Books> Booklist=BooksIO.selectBooks();
        request.setAttribute("Booklist", Booklist);
        session.setAttribute("bookList", Booklist);  
        String url = "/JS/catalog.jsp";
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
        HttpSession session = request.getSession(false);
       
        System.out.println(session.getAttribute("bookList"));
        if(session.getAttribute("user")!=null)
        {
             String userID=session.getAttribute("user").toString();
        int bookID = Integer.parseInt(request.getParameter("bookId"));
        
        updateCart(bookID, userID);
         
        String url = request.getContextPath() + "/MyCart";
        response.sendRedirect(url);
        }else{
                
            request.setAttribute("message", "Please login in order to add to cart");
            String url = "/JS/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        }
        
        /*session.setAttribute("user", userID);
        String url = "/JS/cart.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);*/
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
    
    public void updateCart(int BookID,String userID){
          ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        String query = "SELECT COUNT(1) 'CNT' FROM CART WHERE USERID = ? AND BOOKID = ?";
        
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setInt(2, BookID);
            rs = statement.executeQuery();
            
            while(rs.next()){
                int count = rs.getInt("CNT");
                if (count > 0){
                    String getBookCountQuery = "SELECT QUANTITY FROM CART WHERE USERID = ? AND BOOKID = ?";
                    statement = connection.prepareStatement(getBookCountQuery);
                    statement.setString(1, userID);
                    statement.setInt(2, BookID);
                    rs = statement.executeQuery();
                    
                    while(rs.next()){
                        String updateCartQuery = "UPDATE CART SET QUANTITY = QUANTITY+1 WHERE USERID = ? AND BOOKID = ?";
                        statement = connection.prepareStatement(updateCartQuery);
                    statement.setString(1, userID);
                    statement.setInt(2, BookID);
                    statement.executeUpdate();
                    }
                }else{
                    String insertQuery = "INSERT INTO CART(USERID, BOOKID, QUANTITY) VALUES(?,?,?)";
                    statement = connection.prepareStatement(insertQuery);
                    statement.setString(1, userID);
                    statement.setInt(2, BookID);
                    statement.setInt(3, 1);
                    
                    statement.executeUpdate();
                }
            }
        }catch (SQLException e){
            System.out.println("SQL Connection failed.");
            e.printStackTrace();
            
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}

