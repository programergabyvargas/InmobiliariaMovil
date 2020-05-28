package com.example.moviles2020_2;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviles2020_2.model.Usuario;
import com.example.moviles2020_2.model.GeneralData;

public class LoginViewModel extends ViewModel {
    MutableLiveData<String> error;
    MutableLiveData<Usuario> usuario;


    public LoginViewModel(){
        error = new MutableLiveData<>();
        usuario = new MutableLiveData<>();

    }

    public LiveData<String> getError() {
        if (error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public LiveData<Usuario> getUsuario() {
        if (usuario == null){
            usuario = new MutableLiveData<Usuario>();
        }
        return usuario;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void validar(String mail){

        if (mail.equals("")){

            error.setValue("Verifique usuario y contraseña");
        }else{
            Usuario u = new Usuario(1, "29887502", "Lucero", "Pedro", "2664565685", "pedro@mail.com", "123");
            usuario.setValue(u);
        }

    }
}
