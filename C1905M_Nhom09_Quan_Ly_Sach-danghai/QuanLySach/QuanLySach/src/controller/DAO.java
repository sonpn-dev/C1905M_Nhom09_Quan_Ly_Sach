/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Book;
import model.Category;

/**
 *
 * @author NDH
 */
public class DAO {
    private Connection conn;
    public DAO(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=qlsach;"
            +"username=sa;password=1");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean addBook(Book b){        
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_createBook(?,?,?,?,?)}");
            ps.setString(1,b.getName());
            ps.setString(2,b.getAuthor());
            ps.setString(3,b.getCategory());
            ps.setString(4,b.getNxb());
            ps.setInt(5,b.getYearMaking());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }
    public boolean editBook(Book b){        
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_editBook(?,?,?,?,?,?)}");
            ps.setString(1,b.getName());
            ps.setString(2,b.getAuthor());
            ps.setString(3,b.getCategory());
            ps.setString(4,b.getNxb());
            ps.setInt(5,b.getYearMaking());
            ps.setInt(6,b.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeBook(int remove){        
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_deleteBook(?)}");
            ps.setInt(1, remove);
            int result = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Book> getListBook(){
        ArrayList<Book> list = new ArrayList<>();
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_book_list}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Book c = new Book(); 
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setCategory(rs.getString(3));
                c.setAuthor(rs.getString(4));
                c.setNxb(rs.getString(5));
                c.setYearMaking(rs.getInt(6));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        new DAO();
    }
}
