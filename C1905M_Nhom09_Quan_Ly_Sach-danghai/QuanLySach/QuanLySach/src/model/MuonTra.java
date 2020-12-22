/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class MuonTra {
    private int id;
    private String user;
    private String card;
    private String staff;
    private String statuts;
    private String borrowed_date;
    
    public MuonTra() {
        
    }

    public MuonTra(String user, String card, String staff, String statuts, String borrowed_date) {
        this.user = user;
        this.card = card;
        this.staff = staff;
        this.statuts = statuts;
        this.borrowed_date = borrowed_date;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getCard() {
        return card;
    }

    public String getStaff() {
        return staff;
    }

    public String getStatuts() {
        return statuts;
    }

    public String getBorrowed_date() {
        return borrowed_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    public void setBorrowed_date(String borrowed_date) {
        this.borrowed_date = borrowed_date;
    }
    
    
    
}
