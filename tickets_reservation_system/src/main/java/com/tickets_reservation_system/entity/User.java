package com.tickets_reservation_system.entity;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user", schema = "match_reservation_system")
@ToString
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "pw")
    private String pw;
    @Column(name = "fname")
    private String fname;
    @Column(name = "lname")
    private String lname;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "mail")
    private String mail;
    @Column(name = "user_type")
    private String user_type;
    @Column(name = "approved")
    private Integer approved;



    /*
    @Id
    int id;
    public User(){}

    public User(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    */


    public User(){}

    public User(String username, String pw, String fname, String lname, String dob,
                String gender, String city, String address, String mail, String user_type,
                Integer approved) {
        this.username = username;
        this.pw = pw;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.address = address;
        this.mail = mail;
        this.user_type = user_type;
        this.approved = approved;
    }
    // Setters.
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    // Getters.

    public String getUsername() {
        return username;
    }

    public String getDob() {
        return dob;
    }

    public Integer getApproved() {
        return approved;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getFname() {
        return fname;
    }

    public String getGender() {
        return gender;
    }

    public String getLname() {
        return lname;
    }

    public String getMail() {
        return mail;
    }

    public String getPw() {
        return pw;
    }

    public String getUser_type() {
        return user_type;
    }
}
