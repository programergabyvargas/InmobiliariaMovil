package com.example.moviles2020_2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inmueble implements Serializable {
    private int idInmueble;
    private String direccion;
    private int ambientes;
    private int superficie;
    private double latitud ;
    private double longitud ;
    private int idPropietario;
    private Propietario duenio;
    private Boolean disponible;

    public Inmueble(int idInmueble, String direccion, int ambientes, int superficie, int latitud, int longitud, int idPropietario, Propietario duenio, int i) {
    }

    public Inmueble(int idInmueble, String direccion, int ambientes, int superficie, double latitud, double longitud, int idPropietario, Propietario duenio, Boolean disponible) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idPropietario = idPropietario;
        this.duenio = duenio;
        this.disponible = disponible;
    }


    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
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

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }



    public List<Inmueble> getPropiedades(){
        List<Inmueble> lista;
        lista = new ArrayList<Inmueble>();
        Propietario duenio= new Propietario();
        duenio.setId(5016);
        lista.add(new Inmueble(1, "Sucre 2250", 4, 90, 1356, 1854, 5016, duenio, 1));
       /* lista.add(new Propiedad(2, "Poblet 548", 10, "Depto", "Comercial", 50000, true));
        lista.add(new Propiedad(3, "Bolivar 815", 1, "Depto", "Comercial", 5000, true));
        lista.add(new Propiedad(4, "Colon 3213", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(5, "Lince 22 19", 6, "Depto", "Comercial", 30000, true));
        lista.add(new Propiedad(6, "Italia 11 Sur", 2, "Depto", "Comercial", 10000, true));
        lista.add(new Propiedad(7, "Ruta 3 Km 11", 8, "Depto", "Residencial", 80000, true));
        lista.add(new Propiedad(8, "Ruta 20 km 4", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(9, "Av Illia 185", 4, "Depto", "Comercial", 20000, true));*/
        return lista;
    }
}
