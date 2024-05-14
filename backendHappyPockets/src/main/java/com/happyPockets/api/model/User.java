package com.happyPockets.api.model;

public class User {
    private int id = 0;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname1;
    private String surname2;
    private int phone;

    public User(int id, String username, String password, String email, String name, String surname1, String surname2, int phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public int getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


}
