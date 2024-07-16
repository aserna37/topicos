package com.alura.topicos.model;

public class User {
    private String user;
    private String token;

    // Constructor vacío necesario para que Spring lo utilice en las conversiones JSON
    public User() {
    }

    public User(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
