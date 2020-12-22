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
import model.Staff;
import model.User;
import model.Nxb;

/**
 *
 * @author NDH
 */
public class DAO {

    private Connection conn;

    public DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=qlsach;"
                    + "username=sa;password=1234$");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Book
    public boolean addBook(Book b) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_createBook(?,?,?,?,?)}");
            ps.setString(1, b.getName());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getCategory());
            ps.setString(4, b.getNxb());
            ps.setInt(5, b.getYearMaking());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean editBook(Book b) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_editBook(?,?,?,?,?,?)}");
            ps.setString(1, b.getName());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getCategory());
            ps.setString(4, b.getNxb());
            ps.setInt(5, b.getYearMaking());
            ps.setInt(6, b.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeBook(int remove) {
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

    public ArrayList<Book> getListBook() {
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

    //Staff
    public ArrayList<Staff> getListStaff() {
        ArrayList<Staff> list = new ArrayList<>();
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_listStaff}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff c = new Staff();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setPhone(rs.getString(3));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addStaff(Staff b) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_createStaff(?,?)}");
            ps.setString(1, b.getName());
            ps.setString(2, b.getPhone());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeStaff(int remove) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_deleteStaff(?)}");
            ps.setInt(1, remove);
            int result = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean editStaff(Staff staff) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_editStaff(?,?,?)}");
            ps.setInt(1, staff.getId());
            ps.setString(2, staff.getName());
            ps.setString(3, staff.getPhone());
            int result = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    //User
    public ArrayList<User> getListUser() {
        ArrayList<User> list = new ArrayList<>();
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_listUser}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User c = new User();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setAddress(rs.getString(3));
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
    //Nxb
    public ArrayList<Nxb> getListNxb() {
        ArrayList<Nxb> list = new ArrayList<>();
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_listNxb}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Nxb c = new Nxb();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setAddress(rs.getString(3));
                c.setEmail(rs.getString(4));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addNxb(Nxb b) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_createNxb(?,?,?)}");
            ps.setString(1, b.getName());
            ps.setString(2, b.getAddress());
            ps.setString(3, b.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeNxb(int remove) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_deleteNxb(?)}");
            ps.setInt(1, remove);
            int result = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

    public boolean editNxb(Nxb nxb) {
        try {
            CallableStatement ps = conn.prepareCall("{CALL proc_editNxb(?,?,?,?)}");
            ps.setInt(1, nxb.getId());
            ps.setString(2, nxb.getName());
            ps.setString(3, nxb.getAddress());
            ps.setString(4, nxb.getEmail());
            int result = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return false;
    }

}
