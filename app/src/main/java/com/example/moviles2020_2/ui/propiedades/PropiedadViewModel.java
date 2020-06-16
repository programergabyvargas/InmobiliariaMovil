package com.example.moviles2020_2.ui.propiedades;

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
import com.example.moviles2020_2.model.Propiedad;
import com.example.moviles2020_2.model.Propietario;
import com.example.moviles2020_2.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropiedadViewModel extends AndroidViewModel {
    private MutableLiveData<List<Propiedad>> propiedades;
    private List<Propiedad> listaPropiedades;
    private MutableLiveData<Propiedad> propiedad;
    private int idPropiedad;



    private Propiedad propiedadSelect;
    private Context context;


    public PropiedadViewModel(@NonNull Application application) {
        super(application);
        this.propiedades = new MutableLiveData<List<Propiedad>>();
        this.propiedad = new MutableLiveData<>();
        context = application.getApplicationContext();
        this.listaPropiedades = new ArrayList<Propiedad>();

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

    public void setListaPropiedades(List<Propiedad> propiedades) {
         this.listaPropiedades= propiedades;
    }

    public  List<Propiedad> getListaPropiedades(){
        return listaPropiedades;
    }

    public Propiedad getPropiedadSelect() {
        return propiedadSelect;
    }

    public void setPropiedadSelect(Propiedad propiedadSelect) {
        this.propiedadSelect = propiedadSelect;
    }

        // desde aca lleno el fragment main
    public void obtenerPropiedades(){
        List<Propiedad> lista = new ArrayList<Propiedad>();
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<List<Propiedad>> listaInmueblesCall = ApiClient.getMyApiClient().listaPropiedades(accessToken);
        listaInmueblesCall.enqueue(new Callback<List<Propiedad>>() {
            @Override
            public void onResponse(Call<List<Propiedad>> call, Response<List<Propiedad>> response) {
                if(response.isSuccessful()) {
                    List<Propiedad> lista = new ArrayList<Propiedad>();
                    lista = response.body();
                    setPropiedades(lista);
                    setListaPropiedades(lista);
                    //Log.d("flag","lista de propiedades "+getListaPropiedades().get(0).getId());
                   // Log.d("flag","Tamaño de la lista en obtenerPropiedades : "+ getListaPropiedades().size());
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
        //List<Propiedad> lista = new ArrayList<Propiedad>();
        /*if(!listaPropiedades.isEmpty()) {
            Toast.makeText(context, listaPropiedades.get(0).getId(), Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(context, "lista vacia", Toast.LENGTH_SHORT).show();}*/
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<Propiedad> propiedadCall = ApiClient.getMyApiClient().obtenerPropiedadPorId(accessToken,id);
        propiedadCall.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                if(response.isSuccessful()) {
                    Propiedad propi= response.body();
                    setPropiedad(response.body());
                    setPropiedadSelect(response.body());

                }
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });


      /*           Propiedad prop = lista.stream()
                .filter(x -> id == (x.getId()))
                .findAny()
                .orElse(null);

        if (prop != null){
            setPropiedad(prop);
        }*/

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

    public void obtenerPropiedadPorId(int id){
       // Propiedad prop = new Propiedad();
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<Propiedad> InmuebleCall = ApiClient.getMyApiClient().obtenerPropiedadPorId(accessToken,id);
        InmuebleCall.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                if(response.isSuccessful()) {


                }
            }
            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }


    public void actualizarPropiedad(Propiedad propiedadAct){

        propiedadAct.setId(idPropiedad);
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token","");
        retrofit2.Call<Propiedad> propiedadCall = ApiClient.getMyApiClient().actualizarPropiedad(accessToken, propiedadAct );
        propiedadCall.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                if(response.isSuccessful()) {
                    Propiedad propiedadActualizada = response.body();
                    propiedad.postValue(propiedadActualizada);
                    //existe = propietario;
                    Toast.makeText(context, "Los datos de la propiedad se han actualizado", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {
                Log.d("onFailure", "No entra al response.isSuccesful");
                Log.d("onFailure", t.getMessage());

            }
        });
    }
    public void deletePropiedad(int id){
    SharedPreferences sp = context.getSharedPreferences("token", 0);
    String accessToken = sp.getString("token","");
    retrofit2.Call<Integer> deleteInmuebleCall = ApiClient.getMyApiClient().deleteInmueble(accessToken,id);
        deleteInmuebleCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    int result = response.body();
                    Toast.makeText(context, "Se elimino correctamente el inmueble " +result, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
     });

    }
}
