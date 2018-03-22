/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.business;
import java.io.Serializable;
/**
 *
 * @author Amee
 */
public class Books implements Serializable {
    private int bookID;
    private int authorID;
    private String title;
    private String ISBN;
    private String genre;
    private String type;
    private int publicationYear;
    private double price;
    private String condition;
    private String authorName;
            private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

     public int getBookID() {
        return bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public String getCondition() {
        return condition;
    }
    
   
    
}
