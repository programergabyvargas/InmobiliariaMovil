package com.example.moviles2020_2.ui.propiedades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.moviles2020_2.model.Propiedad;
import com.example.moviles2020_2.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropiedadViewModel extends AndroidViewModel {
    private MutableLiveData<List<Propiedad>> propiedades;
    private MutableLiveData<Propiedad> propiedad;
    private Context context;


    public PropiedadViewModel(@NonNull Application application) {
        super(application);
        this.propiedades = new MutableLiveData<List<Propiedad>>();
        this.propiedad = new MutableLiveData<>();
        context = application.getApplicationContext();
    }

    public LiveData<List<Propiedad>> getPropiedades(){
        if(propiedades==null){
            propiedades=new MutableLiveData<>();
        }
        return propiedades;
    }
    public LiveData<Propiedad> getPropiedad(){
        if(propiedad==null){
            propiedad=new MutableLiveData<>();
        }
        return propiedad;
    }

    public void setPropiedad(Propiedad p){
        this.propiedad.setValue(p);
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades.setValue(propiedades);
    }


    // desde aca lleno el fragment main
    public void obtenerPropiedades(){
        List<Propiedad> lista = new ArrayList<Propiedad>();
      /*  lista.add(new Propiedad(1, "Sucre 2250", 4, "Depto", "Residencial", 10000, true));
        lista.add(new Propiedad(2, "Poblet 548", 10, "Depto", "Comercial", 50000, true));
        lista.add(new Propiedad(3, "Bolivar 815", 1, "Depto", "Comercial", 5000, true));
        lista.add(new Propiedad(4, "Colon 3213", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(5, "Lince 22 19", 6, "Depto", "Comercial", 30000, true));
        lista.add(new Propiedad(6, "Italia 11 Sur", 2, "Depto", "Comercial", 10000, true));
        lista.add(new Propiedad(7, "Ruta 3 Km 11", 8, "Depto", "Residencial", 80000, true));
        lista.add(new Propiedad(8, "Ruta 20 km 4", 3, "Depto", "Residencial", 15000, true));
        lista.add(new Propiedad(9, "Av Illia 185", 4, "Depto", "Comercial", 20000, true));
       */
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<List<Propiedad>> listaInmueblesCall = ApiClient.getMyApiClient().listaPropiedades(accessToken);
        listaInmueblesCall.enqueue(new Callback<List<Propiedad>>() {
            @Override
            public void onResponse(Call<List<Propiedad>> call, Response<List<Propiedad>> response) {
                if(response.isSuccessful()) {
                    List<Propiedad> lista = new ArrayList<Propiedad>();
                    lista = response.body();
                   // propiedades.postValue(lista);
                    setPropiedades(lista);
                }
            }

            @Override
            public void onFailure(Call<List<Propiedad>> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });

        setPropiedades(lista);
    }


    //desde aca lleno el fragment detail
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void obtenerPropiedad(int id){
        List<Propiedad> lista = new ArrayList<Propiedad>();
        lista.add(new Propiedad(1, "Sucre 2250", 4, "Depto", "Residencial",85, 10000, true));
        lista.add(new Propiedad(2, "Poblet 548", 10, "Depto", "Comercial",85, 50000, true));
        lista.add(new Propiedad(3, "Bolivar 815", 1, "Depto", "Comercial", 85,5000, true));
        lista.add(new Propiedad(4, "Colon 3213", 3, "Depto", "Residencial",85, 15000, true));
        lista.add(new Propiedad(5, "Lince 22 19", 6, "Depto", "Comercial", 85,30000, true));
        lista.add(new Propiedad(6, "Italia 11 Sur", 2, "Depto", "Comercial", 85,10000, true));
        lista.add(new Propiedad(7, "Ruta 3 Km 11", 8, "Depto", "Residencial",85, 80000, true));
        lista.add(new Propiedad(8, "Ruta 20 km 4", 3, "Depto", "Residencial", 85,15000, true));
        lista.add(new Propiedad(9, "Av Illia 185", 4, "Depto", "Comercial", 85,20000, true));

       // lista = (List<Propiedad>) propiedades;

        Propiedad prop = lista.stream()
                .filter(x -> id == (x.getId()))
                .findAny()
                .orElse(null);

        if (prop != null){
            setPropiedad(prop);
        }

    }


    //cambiar disponible
    public void cambiarDisponible(){
        try{
            Propiedad p = propiedad.getValue();
            if (p.getDisponible()){
                p.setDisponible(false);
            }else{
                p.setDisponible(true);
            }
            propiedad.setValue(p);
        }catch (Exception e){
            Log.d("verPropVM", e.getMessage());
            Log.d("verPropVM", e.getCause().toString());
        }



    }
}
