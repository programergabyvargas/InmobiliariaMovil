package com.example.moviles2020_2.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviles2020_2.model.Propietario;
import com.example.moviles2020_2.model.Usuario;
import com.example.moviles2020_2.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> propietarioMutableLiveData;
    private Propietario existe;
    private int idPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.propietarioMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Propietario> getPropietario(){
        if (propietarioMutableLiveData==null){
            propietarioMutableLiveData = new MutableLiveData<>();
        }
        return propietarioMutableLiveData;
    }

    public Propietario getExiste(){
        if (existe==null){
            existe = new Propietario();
        }
        return existe;
    }

    public void setUsuario(Propietario u){
        this.propietarioMutableLiveData.postValue(u);
        this.existe = u;
    }

    public void actualizar(Propietario prop){
        int id = getExiste().getId();
        prop.setId(idPropietario);
      //  Propietario propAux = getPropietario().getValue();
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<Propietario> propietarioCall = ApiClient.getMyApiClient().actualizarPropietario(accessToken, prop );
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    Propietario propietario = response.body();
                    propietarioMutableLiveData.postValue(propietario);
                    existe = propietario;
                    Toast.makeText(context, "Sus datos se han actualizado", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("onFailure", "No entra al response.isSuccesful");
                Log.d("onFailure", "No entra al response.isSuccesful");

            }
        });
    }


    public void obtenerPerfil(){
        // aca debo buscar el perfil que inicio session

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<Propietario> propietarioCall = ApiClient.getMyApiClient().perfil(accessToken);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    Propietario propietario = response.body();
                    propietarioMutableLiveData.postValue(propietario);
                    existe = propietario;
                    idPropietario = propietario.getId();
                    Log.d("Flag", "point parada");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("Token", t.getMessage());
            }
        });

    }
}
