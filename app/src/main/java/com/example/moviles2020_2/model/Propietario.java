package com.example.moviles2020_2.model;

public class Propietario {

    private int idPropietario;
    private String dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String email;
    private String clave;

    public Propietario() {
    }

    public Propietario(int  id, String dni, String apellido, String nombre, String telefono, String mail, String clave) {
        this.idPropietario = id;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = mail;

        this.clave = clave;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public int getId() {
        return idPropietario;
    }

    public void setId(int id) {
        this.idPropietario = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}



