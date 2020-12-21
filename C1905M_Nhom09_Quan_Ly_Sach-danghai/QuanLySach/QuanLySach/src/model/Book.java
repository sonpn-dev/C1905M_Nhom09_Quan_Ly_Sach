/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NDH
 */
public class Book {
    private int id;
    private String name;
    private int yearMaking;
    private String author;
    private String category;
    private String nxb;

    public Book() {
    }

    public Book(String name, int yearMaking, String author, String category, String nxb) {
        this.name = name;
        this.yearMaking = yearMaking;
        this.author = author;
        this.category = category;
        this.nxb = nxb;
    }

    public Book(int id, String name, int yearMaking, String author, String category, String nxb) {
        this.id = id;
        this.name = name;
        this.yearMaking = yearMaking;
        this.author = author;
        this.category = category;
        this.nxb = nxb;
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getYearMaking() {
        return yearMaking;
    }

    public void setYearMaking(int yearMaking) {
        this.yearMaking = yearMaking;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    
}
