package com.example.moviles2020_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.moviles2020_2.model.Usuario;

import java.util.List;

import com.example.moviles2020_2.model.Propietario;
import com.example.moviles2020_2.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
public String usuario="carlos@gmail.com", clave="123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        //ObtenerPropietario();
        login();
    }

    public void ObtenerPropietario(){
        Call<List<Propietario>> datos= ApiClient.getMyApiClient().obtenerPropietarios();
        datos.enqueue(new Callback<List<Propietario>>() { //otro hilo/proceso
            @Override
            public void onResponse(Call<List<Propietario>> call, Response<List<Propietario>> response) {
                if(response.isSuccessful()) {
                    Log.d("email : ", response.body().get(0).getApellido());
                    Log.d("clave :", response.body().get(0).getClave());
                    //lista.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Propietario>> call, Throwable t) {
                Log.d("nombre","no pudo leer");
            }
        });
    }

    public void login(){

        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setClave(clave);
        Call<String> token= ApiClient.getMyApiClient().login(user);
        Log.d("token_____: ", String.valueOf(token));
        token.enqueue(new Callback<String>() { //otro hilo/proceso
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Log.d("token --->: ", response.body());

                    //uardar en preferences
                    //token.postValue(response.body()); variable mutable
                    SharedPreferences sp = getSharedPreferences("token" , 0);
                    SharedPreferences.Editor editor =sp.edit();
                    String t = "Bearer " + response.body();
                    editor.commit();
                    Log.d("salida ultimo token", t);

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Token", t.getMessage());
                Log.d("Rama del onFilure-----", t.getMessage());


            }
        });

    }
}
