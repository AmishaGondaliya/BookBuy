package books.controllers;

import books.Data.*;
import books.business.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


@WebServlet(name = "AdminController", urlPatterns = {"/admincontroller","/admin/AddBook","/admin/ReviewQuantity"})
public class AdminController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/admin";
        if (requestURI.endsWith("/AddBook") && (request.getParameter("bookName")!= null)) {
   String title=request.getParameter("bookName");
   String ISBN=request.getParameter("ISBN");
   String PublicationYear=request.getParameter("publicationYear");
   String Genre=request.getParameter("genre");
   String type=request.getParameter("type");
   String price=request.getParameter("price");
   String bookcondition=request.getParameter("condition");
   String quantity=request.getParameter("quantity");
   Books b=new Books();
   b.setTitle(title);
   b.setCondition(bookcondition);
   b.setISBN(ISBN);
   b.setPublicationYear(Integer.parseInt(PublicationYear));
   b.setQuantity(Integer.parseInt(quantity));
   b.setPrice(Double.parseDouble(price));
   b.setType(type);
   b.setGenre(Genre);
   BooksIO.addBook(b);
        }
        else{
            url="/admin/adminhome.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

      String requestURI = request.getRequestURI();
      String url;
     if(requestURI.endsWith("/AddBook"))
     {
       url="/Admin/AddBook.jsp";
     }
     else
     {
         url="/Admin/adminhome.jsp";
     }
            
        
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
         
       
    }

   
    
  
}