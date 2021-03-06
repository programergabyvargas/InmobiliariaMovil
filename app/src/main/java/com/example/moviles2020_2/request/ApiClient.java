package com.example.moviles2020_2.request;

import android.util.Log;

import com.example.moviles2020_2.model.Propiedad;
import com.example.moviles2020_2.model.Propietario;
import com.example.moviles2020_2.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {

    private static final String PATH="http://192.168.0.126:45455/api/";
    private static  MyApiInterface myApiInteface;

    public static MyApiInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {
        @GET("Propietarios")
        Call<List<Propietario>> obtenerPropietarios();

        @POST("Propietarios/Login")
        Call<String> login(@Body Usuario usuario);

        //Con parametros
        //@POST("Propietarios/Login")
        //Call<String> login(@Query("Usuario") String usuario, @Query("Clave") String clave);

        @GET("Propietarios")
        Call<Propietario> perfil(@Header("Authorization") String token);

        @PUT("Propietarios")
        Call<Propietario> actualizarPropietario(@Header("Authorization") String token, @Body Propietario popietario);

        @GET("Inmuebles")
        Call<List<Propiedad>> listaPropiedades(@Header("Authorization") String token);

        @GET("Inmuebles/{id}")
        Call<Propiedad> obtenerPropiedadPorId(@Header("Authorization") String token, @Path("id") int id);


        // ABM de Inmuebles
        @POST("Inmuebles")
        Call<Propiedad> altaPropiedad(@Header("Authorization") String token, @Body Propiedad propiedad);

        @PUT("Inmuebles/{id}")
        Call<Propiedad> actualizarPropiedad(@Header("Authorization") String token, @Path("id") int id, @Body Propiedad propiedad);

        @DELETE("Inmuebles/{id}")
        Call<Integer> deleteInmueble(@Header("Authorization") String token,  @Path("id") int id);




    }
}
