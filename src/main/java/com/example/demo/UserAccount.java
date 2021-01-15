package com.example.demo;

import javax.persistence.*;

/* Map this entity class to user_account table. */
@Entity(name = "user_account")
public class UserAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    private String report;
    private String firstname;
    private String lastname;
    private String phone;
    private String thing1;
    private String thing2;
    private String thing3;
    private String about;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getThing1() {
        return thing1;
    }

    public void setThing1(String thing1) {
        this.thing1 = thing1;
    }

    public String getThing2() {
        return thing2;
    }

    public void setThing2(String thing2) {
        this.thing2 = thing2;
    }

    public String getThing3() {
        return thing3;
    }

    public void setThing3(String thing3) {
        this.thing3 = thing3;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}