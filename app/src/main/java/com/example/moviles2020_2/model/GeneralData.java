package com.example.moviles2020_2.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class GeneralData {




    private static List<Usuario> lista;
    private List<Propiedad> propiedads;

    public GeneralData() {

        lista = new ArrayList<Usuario>();
        Usuario uno = new Usuario( "a@mail.com", "123");
        lista.add(uno);
        Usuario dos = new Usuario("b@mail.com", "123");
        lista.add(dos);
        Usuario tres = new Usuario("c@mail.com", "123");
        lista.add(tres);
        //Usuario cuatro = new Usuario(4, "41256323", "Ramos", "Pamela", "2665451236", "d@mail.com", "123");
        //lista.add(cuatro);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Usuario obtenerPorMail(String mail){
        return lista.stream()
                .filter(x -> mail.equals(x.getUsuario()))
                .findAny()
                .orElse(null);
    }





}
