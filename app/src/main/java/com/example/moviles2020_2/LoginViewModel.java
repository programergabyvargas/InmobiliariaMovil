package com.example.moviles2020_2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviles2020_2.model.Usuario;
import com.example.moviles2020_2.model.GeneralData;
import com.example.moviles2020_2.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> error;
    private MutableLiveData<String> token;
    private MutableLiveData<Usuario> usuario;
    public String nameUsuario, clave;

    private Context context;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        error = new MutableLiveData<>();
        token = new MutableLiveData<>();
        usuario = new MutableLiveData<>();
        //nameUsuario = "carlos@gmail.com";
        //clave = "123";

    }

    public LiveData<String> getToken() {
        if (token == null) {
            token = new MutableLiveData<>();
        }
        return token;
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
            Usuario u = new Usuario( "gaby@mail.com", "123");
            usuario.setValue(u);
        }

    }

    public void ingresar(String nameUsuario, String pass) {

        Log.d("nameUsuario : ", nameUsuario);
        Log.d("Clave : ", pass);

        Usuario user = new Usuario();
        user.setUsuario(nameUsuario);
        user.setClave(pass);
        Call<String> strToken = ApiClient.getMyApiClient().login(user);
        Log.d("token_____: ", String.valueOf(strToken));
        strToken.enqueue(new Callback<String>() { //otro hilo/proceso
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("----------token --->: ", response.body());
                    token.postValue(response.body()); //variable mutable
                    SharedPreferences sp = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    String t = "Bearer "+response.body();
                    editor.putString("token", t);
                    editor.commit();
                    Log.d("salida ultimo token", t);
                }else {
                    Toast.makeText(context, "Usuario y/o Contraseña Invalidos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log.d("Token", t.getMessage());
                Log.d("Rama del onFilure-----", t.getMessage());



            }
        });


    }
}
