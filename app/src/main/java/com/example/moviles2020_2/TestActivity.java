package com.example.moviles2020_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.moviles2020_2.model.Propietario;
import com.example.moviles2020_2.model.Usuario;

import java.util.List;

import request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
public String mail="carlos@gmail.com", clave="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       // ObtenerPropietario();
        login();
    }

    public void ObtenerPropietario(){
        Call<List<Propietario>> datos= ApiClient.getMyApiClient().obtenerPropietarios();
        datos.enqueue(new Callback<List<Propietario>>() { //otro hilo/proceso
            @Override
            public void onResponse(Call<List<Propietario>> call, Response<List<Propietario>> response) {
                if(response.isSuccessful()) {
                    Log.d("email : ", response.body().get(0).getEmail());
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

        Propietario propietario = new Propietario(mail, clave);
        Call<String> token= ApiClient.getMyApiClient().login(propietario);
        Log.d("token_____: ", String.valueOf(token));
        token.enqueue(new Callback<String>() { //otro hilo/proceso
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Log.d("token --->: ", response.body());

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Token","no pudo leer");

            }
        });

    }
}
