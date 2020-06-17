package com.example.moviles2020_2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Propiedad implements Serializable {
    private int idInmueble;
    private String direccion;
    private int ambientes;
    private String tipo;
    private  String uso;
    private int superficie;
    private double precio;
    private Boolean disponible;
    private int idPropietario;
   // private Usuario usuario;

    public Propiedad() {
    }

    public Propiedad(int id, String direccion, int ambientes, String tipo, String uso, int superficie, double precio, Boolean disponible, int idPropietario) {
        this.idInmueble = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.superficie = superficie;
        this.precio = precio;
        this.disponible = disponible;
        this.idPropietario = idPropietario;
    }

    public Propiedad( String direccion, int ambientes, String tipo, String uso, double precio, Boolean disponible) {
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.superficie = superficie;
        this.precio = precio;
        this.disponible = disponible;
        this.idPropietario = idPropietario;
    }

    public int getId() {
        return idInmueble;
    }

    public void setId(int id) {
        this.idInmueble = id;
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

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

   /* public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
*/

    public List<Propiedad> getPropiedades(){
        List<Propiedad> lista;
        lista = new ArrayList<Propiedad>();
        lista.add(new Propiedad(1, "Sucre 2250", 4, "Depto", "Residencial", 85,10000, true,5016));
        lista.add(new Propiedad(2, "Poblet 548", 10, "Depto", "Comercial", 90,50000, true,5016));
        lista.add(new Propiedad(3, "Bolivar 815", 1, "Depto", "Comercial", 75,5000, true,5016));
        lista.add(new Propiedad(4, "Colon 3213", 3, "Depto", "Residencial", 56,15000, true,5016));
        lista.add(new Propiedad(5, "Lince 22 19", 6, "Depto", "Comercial", 90,30000, true,5016));
        lista.add(new Propiedad(6, "Italia 11 Sur", 2, "Depto", "Comercial", 85,10000, true,5016));
        lista.add(new Propiedad(7, "Ruta 3 Km 11", 8, "Depto", "Residencial",75, 80000, true,5016));
        lista.add(new Propiedad(8, "Ruta 20 km 4", 3, "Depto", "Residencial",65, 15000, true,5016));
        lista.add(new Propiedad(9, "Av Illia 185", 4, "Depto", "Comercial",89, 20000, true,5016));
        return lista;
    }
}
