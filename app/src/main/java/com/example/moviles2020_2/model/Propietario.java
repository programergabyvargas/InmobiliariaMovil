package com.example.moviles2020_2.model;

public class Propietario {
    private String email;
    private String clave;

    public Propietario(String email, String clave) {
        this.email = email;
        this.clave = clave;
    }
    public Propietario() {    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) { this.clave = clave; }
}
