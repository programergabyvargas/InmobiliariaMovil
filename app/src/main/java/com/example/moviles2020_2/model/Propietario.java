package com.example.moviles2020_2.model;

public class Propietario {
    private String usuario;
    private String clave;

    public Propietario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    public Propietario() {    }

    public String getEmail() { return usuario; }

    public void setEmail(String email) { this.usuario = email; }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) { this.clave = clave; }
}
