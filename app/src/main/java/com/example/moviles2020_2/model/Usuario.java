package com.example.moviles2020_2.model;

public class Usuario {

    private String usuario;
    private String clave;

    public Usuario(String usuario, String clave) {

        this.usuario = usuario;
        this.clave = clave;
    }
    public Usuario() {    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String email) { this.usuario = email; }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) { this.clave = clave; }

}
