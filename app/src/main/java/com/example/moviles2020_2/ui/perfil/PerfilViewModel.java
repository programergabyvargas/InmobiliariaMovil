package com.example.moviles2020_2.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviles2020_2.model.Usuario;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;

    public PerfilViewModel() {
        this.usuario = new MutableLiveData<>();
    }

    public LiveData<Usuario> getUsuario(){
        if (usuario==null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }

    public void setUsuario(Usuario u){
        this.usuario.setValue(u);
    }

    public void obtenerPerfil(String mail){
        // aca debo buscar el perfil que inicio session

        Usuario u = new Usuario(
                1,
                "32872636",
                "Gabriel",
                "Vargas",
                "2664678211",
                "gabrielvargas1807@gmail.com",
                "123");

        setUsuario(u);
    }
}
