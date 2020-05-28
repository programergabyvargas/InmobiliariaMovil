package com.example.moviles2020_2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Propiedad implements Serializable {
    private int id;
    private String direccion;
    private int ambientes;
    private String tipo;
    private  String uso;
    private double precio;
    private Boolean disponible;
    private Usuario usuario;

    public Propiedad(int id, String direccion, int ambientes, String tipo, String uso, double precio, Boolean disponible) {
        this.id = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public List<Propiedad> getPropiedades(){
        List<Propiedad> lista;
        lista = new ArrayList<Propiedad>();
        lista.add(new Propiedad(1, "Sucre 2250", 4, "Depto", "Residencial", 10000, true));
        lista.add(new Propiedad(2, "Poblet 548", 10, "Depto", "Comercial", 50000, true));
        lista.add(new Propiedad(3, "Bolivar 815", 1, "Depto", "Comercial", 5000, true));
        lista.add(new Propiedad(4, "Colon 3213", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(5, "Lince 22 19", 6, "Depto", "Comercial", 30000, true));
        lista.add(new Propiedad(6, "Italia 11 Sur", 2, "Depto", "Comercial", 10000, true));
        lista.add(new Propiedad(7, "Ruta 3 Km 11", 8, "Depto", "Residencial", 80000, true));
        lista.add(new Propiedad(8, "Ruta 20 km 4", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(9, "Av Illia 185", 4, "Depto", "Comercial", 20000, true));
        return lista;
    }
}
